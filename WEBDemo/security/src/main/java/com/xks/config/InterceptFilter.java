package com.xks.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * 拦截器和过滤器的区别：
 * 1.拦截器是基于java反射机制，而过滤器是基于函数的回调
 * 2.拦截器不依赖于servlet容器，而过滤器相反
 * 3.拦截器只对action请求有作用，而过滤器几乎对所有请求都有效
 * 4.拦截器可以访问action上下文，值栈里的值，而过滤器不能
 * 5.在action生命周期中，拦截器可以被执行多次，而过滤器只有在容器初始化时执行一次
 * 执行顺序 ：过滤前 - 拦截前 -Action处理 - 拦截后 -过滤后。
 * 个人认为过滤是一个横向的过程，首先把客户端提交的内容进行过滤(例如未登录用户不能访问内部页面的处理)；
 * 过滤通过后，拦截器将检查用户提交数据的验证，做一些前期的数据处理，接着把处理后的数据发给对应的Action；
 * Action处理完成返回后，拦截器还可以做其他过程(还没想到要做啥)，再向上返回到过滤器的后续操作。
 *
 * @author xks
 * @date 2019-08-19
 */
@Slf4j
public class InterceptFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * @param enabled               如果启用了该用户 设置为true
     * @param accountNonExpired     如果帐户没有过期的话 设置为true
     * @param credentialsNonExpired 如果凭据没有  设置为true
     * @param accountNonLocked      如果帐户没有被锁定 设置为true
     * @param authorities           权限列表
     *                              <p>
     *                              credentials 凭证
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//      List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        User xks = new User("xks", "123", true,
                true, true, true, new ArrayList());
        log.info("验证当前用户");
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(xks, xks.getPassword(), new ArrayList());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
