package me.nikesh.jbot.core.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for different Event types in  Fb Messenger Bot API.
 * 
 * @author nikesh
 * @version 1.0.0, 12/06/2016.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Controller {
    EventType[] events() default EventType.MESSAGE;
    
    String pattern() default "";
    
    String next() default "";
}
