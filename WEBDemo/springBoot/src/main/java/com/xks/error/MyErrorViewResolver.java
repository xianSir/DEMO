package com.xks.error;

import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 发生异常跳入错误页面
 *
 * @author xks
 * @date 2019-08-09
 */
public class MyErrorViewResolver implements ErrorViewResolver {

    @Override
    public ModelAndView resolveErrorView(HttpServletRequest request,
                                         HttpStatus status, Map<String, Object> model) {
        ModelAndView view = new ModelAndView();
        //view.setStatus(HttpStatus.NOT_FOUND); 或
        view.setViewName("errorPage.html");
        return view;
    }

}