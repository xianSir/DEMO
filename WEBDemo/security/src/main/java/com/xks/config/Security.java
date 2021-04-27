
package com.xks.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


/**
 * @author xks
 * @EnableWebSecurity 使用这个注解将彻底控制springsecurity  自动放弃springBoot 的自动配置
 * @date 2019-08-14
 */
@EnableWebSecurity
@Slf4j
public class Security extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        log.info("启用配置");
        //修改内存用户名  密码
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withDefaultPasswordEncoder().password("123").username("xks").roles("USER", "tets", "root").build());
        return manager;
    }

    public void configure(WebSecurity web) throws Exception {
        web.ignoring().mvcMatchers("/",
                "/index1.html",
                "/css/**",
                "/font-awesome-4.7.0/**",
                "/font/**",
                "/img/**",
                "/js/**",
                "/scripts/**",
                "/styles/**"
        );
    }

    /**
     * 自定义配置页面或请求
     * @param http
     * @throws Exception
     */
/*  @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.info("自定义配置页面");
        http.authorizeRequests().anyRequest().permitAll()//对url验证
        .and()
        .formLogin().loginPage("/test.html").permitAll()//配置登录页面
        .and()
        .logout().logoutUrl("/logout") //触发注销的URL（默认为/logout）
                .logoutSuccessHandler(new MyLogoutSuccessHandler())  //指定一个自定义LogoutSuccessHandler。如果指定了，logoutSuccessUrl()则忽略
                .logoutSuccessUrl("/logoutSuccess") //注销后重定向到的URL
                .invalidateHttpSession(true)//指定HttpSession在注销时是否使其无效。默认情况下这是真的
                .addLogoutHandler(new MyLogoutHander()) //添加一个LogoutHandler。默认情况下SecurityContextLogoutHandler添加为最后一个LogoutHandler。
                .deleteCookies() //允许指定在注销成功时删除的cookie的名称
        ;
    }*/

    /**
     * 自定义登录配置 或覆盖 userDetailsService()
     * @param auth
     * @throws Exception
     */
 /*   @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService()
        auth.jdbcAuthentication();
        auth.authenticationProvider()
        auth.userDetailsService()
    }*/
}

