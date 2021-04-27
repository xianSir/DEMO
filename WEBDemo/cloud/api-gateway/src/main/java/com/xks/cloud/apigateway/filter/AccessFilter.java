package com.xks.cloud.apigateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author xks
 * @date 2019-09-02
 */
@Component
public class AccessFilter extends ZuulFilter {
    /**
     * to classify a filter by type. Standard types in Zuul are "pre" for pre-routing filtering,
     * "route" for routing to an origin, "post" for post-routing filters, "error" for error handling.
     * We also support a "static" type for static responses see  StaticResponseFilter.
     * Any filterType made be created or added and run by calling FilterProcessor.runFilters(type)
     *
     * @return A String representing that type
     */
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * a "true" return from this method means that the run() method should be invoked
     *
     * @return true if the run() method should be invoked. false will not invoke the run() method
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("网关过滤器运行中------------------------------");
        RequestContext requestContext = RequestContext.getCurrentContext();
        //设置编码格式
        HttpServletResponse response = requestContext.getResponse();
        String encoding = response.getCharacterEncoding();
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        System.out.println(encoding);
        HttpServletRequest request = requestContext.getRequest();
        String name = request.getParameter("name");
        if(StringUtils.equals("xks", name)){
            return null;
        }else {
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseBody("服务器忙,请确认你要的东西是否合法");
            return null;
        }
    }
}
