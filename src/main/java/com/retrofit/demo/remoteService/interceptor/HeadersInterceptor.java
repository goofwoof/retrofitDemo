package com.retrofit.demo.remoteService.interceptor;

import com.github.lianjiatech.retrofit.spring.boot.annotation.InterceptMark;
import com.github.lianjiatech.retrofit.spring.boot.interceptor.BasePathMatchInterceptor;
import lombok.val;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class HeadersInterceptor extends BasePathMatchInterceptor {
    @Value("${delegate.headers}")
    private String[] headers;

    @Override
    protected Response doIntercept(Chain chain) throws IOException {
        Assert.notEmpty(headers,"config delegate.header should not empty");
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (!(requestAttributes instanceof ServletRequestAttributes)){
            throw new RuntimeException("servlet attributes is null");
        }
        ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;
        Request request = chain.request();
        val builder = request.newBuilder();
        setHeaders(builder, attributes.getRequest(), headers);
        return chain.proceed(builder.build());
    }

    private void setHeaders(Request.Builder builder, HttpServletRequest request, String[] headers) {
        for (String header : headers) {
            String headerInfo = request.getHeader(header);
            Assert.isTrue(!StringUtils.isEmpty(headerInfo),"Error Header Info.");
            builder.addHeader(header, headerInfo);
        }
    }
}
