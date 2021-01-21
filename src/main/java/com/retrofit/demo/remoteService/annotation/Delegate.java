package com.retrofit.demo.remoteService.annotation;


import com.github.lianjiatech.retrofit.spring.boot.annotation.InterceptMark;
import com.github.lianjiatech.retrofit.spring.boot.interceptor.BasePathMatchInterceptor;
import com.retrofit.demo.remoteService.interceptor.HeadersInterceptor;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@InterceptMark
public @interface Delegate {
    /**
     * 拦截器匹配路径
     *
     * @return 拦截器匹配路径
     */
    String[] include() default {"/**"};

    /**
     * 拦截器排除匹配，排除指定路径拦截
     *
     * @return 排除指定路径拦截
     */
    String[] exclude() default {};

    /**
     * Interceptor handler
     * 优先从spring容器获取对应的Bean，如果获取不到，则使用反射创建一个！
     * First obtain the corresponding Bean from the spring container, if not, use reflection to create one!
     *
     * @return 拦截器处理器 Interceptor handler
     */
    Class<? extends BasePathMatchInterceptor> handler() default HeadersInterceptor.class;
}
