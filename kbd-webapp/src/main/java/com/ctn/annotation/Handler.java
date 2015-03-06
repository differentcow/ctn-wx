package com.ctn.annotation;

import com.ctn.entity.type.EventType;
import com.ctn.entity.type.MsgType;

import java.lang.annotation.*;

/**
 * Created by barry on 2014/10/20.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Handler {
    MsgType value() default MsgType.text;
    EventType type() default EventType.subscribe;
}
