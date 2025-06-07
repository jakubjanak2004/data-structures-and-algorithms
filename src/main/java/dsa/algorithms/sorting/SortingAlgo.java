package dsa.algorithms.sorting;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for sorting methods, they will be automatically injected in the Tests.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SortingAlgo {
    boolean inplace() default true;
    boolean onlyInteger() default false;
}
