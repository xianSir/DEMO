package com.xks.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

/**
 * http缓存
 *
 * @author xks
 * @date 2019-08-05
 * <p>
 * ShallowEtagHeaderFilter
 */
public class HttpCacheDemo1Controller {
    @RequestMapping
    public String myHandleMethod(WebRequest webRequest, Model model) {

        String eTag = webRequest.getHeader("etag");

        if(webRequest.checkNotModified(eTag)) {
            return null;
        }

        model.addAttribute("");
        return "myViewName";
    }
}
