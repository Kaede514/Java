package com.kaede.L_Annotation;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

/**
 * @author kaede
 * @create 2022-10-10
 */

@Repeatable(MyAnnotations.class)
@Inherited
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE,TYPE_PARAMETER,TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {

    String value() default "hello";

}
