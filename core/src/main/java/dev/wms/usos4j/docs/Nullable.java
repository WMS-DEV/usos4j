package dev.wms.usos4j.docs;

import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target(value = { METHOD, FIELD, PARAMETER, LOCAL_VARIABLE, TYPE_USE })
public @interface Nullable {
}
