package pl.wmsdev.usos4j.arch;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaModifier;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import com.tngtech.archunit.lang.syntax.elements.GivenClassesConjunction;
import org.slf4j.Logger;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "pl.wmsdev.usos4j")
public class UsosApiArchTest {

    @ArchTest
    private final ArchRule api_classes_having_api_suffix =
            apiClasses()
                    .should().haveNameMatching(".*API\\$?\\d*");

    @ArchTest
    private final ArchRule no_info_logs_in_api =
            noApiClasses()
                    .should().callMethod(Logger.class, "info")
                    .because("we should use other logging levels in api classes");

    @ArchTest
    private final ArchRule public_return_types_should_reside_in_our_package_or_primitives =
            apiClasses()
                    .should(new ArchCondition<JavaClass>("return only our classes, or java classes") {
                        @Override
                        public void check(JavaClass javaClass, ConditionEvents conditionEvents) {
                            StringBuilder message = new StringBuilder();
                            var badMethods = javaClass.getMethods()
                                    .stream()
                                    .filter(method -> method.getModifiers().contains(JavaModifier.PUBLIC))
                                    .filter(method -> !(method.getRawReturnType().isPrimitive() || method.getRawReturnType().getPackageName().contains("pl.wmsdev.usos4j")
                                            || method.getRawReturnType().getSimpleName().equals("void") || method.getRawReturnType().getPackageName().startsWith("java.")))
                                    .toList();
                            for (var method : badMethods) {
                                message.append("%s in %s return %s which resides in package %s but should reside in %s instead "
                                        .formatted(method.getName(), javaClass.getSimpleName(), method.getReturnType().getName(), method.getRawReturnType().getPackageName(), "pl.wmsdev.usos4j.*"));
                            }
                            conditionEvents.add(new SimpleConditionEvent(javaClass, badMethods.isEmpty(), message.toString()));
                        }
                    });

    @ArchTest
    private final ArchRule public_method_names_should_not_contain_get_or_set =
            methods()
                    .that().areDeclaredInClassesThat().resideInAPackage("pl.wmsdev.usos4j.api.*")
                    .and().arePublic()
                    .should()
                    .haveNameNotStartingWith("get")
                    .andShould()
                    .haveNameNotStartingWith("set");

    @ArchTest
    private final ArchRule public_methods_should_return_classes_prefixed_with_usos =
            apiMethods()
                    .should(new ArchCondition<JavaClass>("return only our classes, or java classes") {
                        @Override
                        public void check(JavaClass javaClass, ConditionEvents conditionEvents) {
                            StringBuilder message = new StringBuilder();
                            var badMethods = javaClass.getMethods()
                                    .stream()
                                    .filter(method -> !(method.getRawReturnType().isPrimitive() || method.getRawReturnType().getSimpleName().startsWith("Usos")
                                            || method.getRawReturnType().getSimpleName().equals("void") || method.getRawReturnType().getPackageName().startsWith("java.")))
                                    .toList();
                            for (var method : badMethods) {
                                message.append("%s in %s return %s which doesn't have prefix Usos "
                                        .formatted(method.getName(), javaClass.getSimpleName(), method.getReturnType().getName()));
                            }
                            conditionEvents.add(new SimpleConditionEvent(javaClass, badMethods.isEmpty(), message.toString()));
                        }
                    });

    private GivenClassesConjunction apiClasses() {
        return classes()
                .that().resideInAPackage("pl.wmsdev.usos4j.api.*");
    }

    private GivenClassesConjunction apiMethods() {
        return apiClasses()
                .and().arePublic();
    }

    private GivenClassesConjunction noApiClasses() {
        return noClasses()
                .that().resideInAPackage("pl.wmsdev.usos4j.api.*");
    }

}
