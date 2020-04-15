package com.springcloud.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import jdk.nashorn.internal.runtime.logging.Logger;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import javax.servlet.http.HttpServletRequest;

@Configuration
public class LogFilter extends ZuulFilter {
    @Override
    public String filterType() {
        //前置过滤器
        return FilterConstants.ROUTE_TYPE;
    }
    // RequestContext 是默认级别的， 如果要取到他，需要在他之后取
    @Override
    public int filterOrder() {
        //优先级 默认过滤器级别
        return FilterConstants.PRE_DECORATION_FILTER_ORDER+1;
    }

    @Override
    public boolean shouldFilter() {
        //是否启用
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //过滤逻辑
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        System.out.println(request.getRemoteAddr()+"访问了"+request.getRequestURL());
        return null;
    }
}
