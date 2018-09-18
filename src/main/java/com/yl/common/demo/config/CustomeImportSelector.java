package com.yl.common.demo.config;

import com.yl.common.demo.ImportSelectorClass;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author Alex
 * @since 2018/8/30 09:56
 */
public class CustomeImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata metadata) {
        /**
         * 返回需要注册的组件的全类名
         *      注意:此处不得返回null
         */
        return new String[]{ImportSelectorClass.class.getName()};
    }
}
