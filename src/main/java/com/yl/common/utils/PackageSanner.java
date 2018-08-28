package com.yl.common.utils;

import com.sun.tools.javac.util.Assert;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.TypeFilter;

import java.util.Set;

/**
 * @author Alex
 * @since 2018/8/28 10:28
 * @des 自定义包扫描器 需自己实现扫描过滤规则
 */
public class PackageSanner {

    public Set<BeanDefinition> doScan(String pkg,FilterProvider filterProvider){
        Assert.checkNonNull(pkg);
        Assert.checkNonNull(filterProvider);
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        if(filterProvider.addFilter() != null){
            for (TypeFilter typeFilter:filterProvider.addFilter()){
                scanner.addIncludeFilter(typeFilter);
            }
        }

        if(filterProvider.subFilter() != null){
            for (TypeFilter typeFilter:filterProvider.subFilter()){
                scanner.addExcludeFilter(typeFilter);
            }
        }
        return scanner.findCandidateComponents(pkg);
    }


    /**
     * 自定义扫描过滤器
     */
    public interface FilterProvider {
        public Set<TypeFilter> addFilter();
        public Set<TypeFilter> subFilter();
    }

}
