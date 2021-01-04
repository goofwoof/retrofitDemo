package com.retrofit.demo.remoteService.interceptor;

import com.github.lianjiatech.retrofit.spring.boot.interceptor.BasePathMatchInterceptor;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class CookieInterceptor extends BasePathMatchInterceptor {
    @Override
    protected Response doIntercept(Chain chain) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (!(requestAttributes instanceof ServletRequestAttributes)){
            throw new RuntimeException("servlet attributes is null");
        }
        ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;
        attributes.getRequest().getCookies()
        Request request = chain.request();
        Request newRequest = request.newBuilder()
                .addHeader()
                .build();
        return chain.proceed(newRequest);
    }
}
