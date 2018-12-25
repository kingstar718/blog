package top.wujinxing.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 安全配置类
 * @author: wujinxing
 * @date: 2018/12/20 10:52
 * @description:
 */
@EnableWebSecurity  //认证安全的注解
@EnableGlobalMethodSecurity(prePostEnabled = true)  //启用方法安全设置
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String KEY = "waylau.com";

     //有待商榷  因为UserServiceImpl  重写了该方法


    @Autowired
     private UserDetailsService userDetailsService;

     @Autowired
     private PasswordEncoder passwordEncoder;

     @Bean
     public PasswordEncoder passwordEncoder(){
         return new BCryptPasswordEncoder();    //使用BCrypt加密
     }

     @Bean
     public AuthenticationProvider authenticationProvider(){
         DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
         authenticationProvider.setUserDetailsService(userDetailsService);
         authenticationProvider.setPasswordEncoder(passwordEncoder);      //设置加密方式
         return authenticationProvider;
     }

   /**
     * 自定义配置
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/css/**","/js/**","/fonts/**","index").permitAll()    //都可以访问
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/admins/**").hasRole("ADMIN")
                .and()
                .formLogin()    //基于form表单验证登录
                .loginPage("/login").failureUrl("/login-error")    //自定义登录界面*/
                .and().rememberMe().key(KEY)    //启用remember me
                .and().exceptionHandling().accessDeniedPage("/403");    //处理异常, 拒绝访问就重定向到403页面
        http.csrf().ignoringAntMatchers("/h2-console/**");  //禁用H2控制台的CSRF防护
        http.headers().frameOptions().sameOrigin(); //允许同一来源的H2控制台的请求
    }

    /*@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth
                .inMemoryAuthentication()   //认证信息存储于内存中
                    .passwordEncoder(new BCryptPasswordEncoder())
                    //.withUser("wujinxing718").password("123456").roles("ADMIN");
                .withUser("wujinxing718").password(new BCryptPasswordEncoder().encode("123456")).roles("ADMIN");
    }*/


    /**
     * 认证信息管理   //由于注册时账号未做加密, 必须使用数据库的初始账号 admin  123456
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(authenticationProvider());
    }


}
