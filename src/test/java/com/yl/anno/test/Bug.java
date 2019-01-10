package com.yl.anno.test;

import java.lang.annotation.*;

/**
 * @author Alex
 * @since 2019/1/3 16:56
 */
@Target(value = {ElementType.FIELD})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface Bug {

    public Class<? extends Selector> selector();
    
}
