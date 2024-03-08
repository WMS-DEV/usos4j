package dev.wms.usos4j.arch;

import com.tngtech.archunit.core.domain.JavaModifier;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;

@AnalyzeClasses(packages = "dev.wms.usos4j.utils")
public class Usos4jUtilsArchTest {

    @ArchTest
    private final ArchRule utils_classes_should_be_final =
            classes()
                    .should().bePublic()
                    .andShould().haveModifier(JavaModifier.FINAL);

    @ArchTest
    private final ArchRule utils_classes_should_have_only_static_methods =
            methods().should().beStatic();

}
