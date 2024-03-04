package com.harvey.configuration;

import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWT;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.harvey.common.HttpConstant;
import com.harvey.common.HttpHeader;
import com.harvey.common.RedisKey;
import com.harvey.common.Result;
import com.harvey.domain.*;
import com.harvey.filter.MyTokenFilter;
import com.harvey.handler.*;
import com.harvey.util.ResponseUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.List;

@Configuration
@EnableMethodSecurity
public class SecurityConfiguration {
    @Bean
    SecurityFilterChain securityFilterChain(
        HttpSecurity httpSecurity,
        AuthenticationManager authenticationManager,
        MyTokenFilter myTokenFilter,
        MyAuthenticationSuccessHandler myAuthenticationSuccessHandler,
        MyAuthenticationFailureHandler myAuthenticationFailureHandler,
        MyLogoutHandler myLogoutHandler,
        MyLogoutSuccessHandler myLogoutSuccessHandler,
        MyAuthenticationEntryPoint myAuthenticationEntryPoint,
        MyAccessDeniedHandler myAccessDeniedHandler
    ) throws Exception {
        httpSecurity.authorizeHttpRequests((authorize) -> {
            authorize.requestMatchers("/login", "/logout").permitAll()
                .anyRequest().authenticated();
        });
        
        httpSecurity.formLogin((formLogin) -> {
            formLogin.loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailureHandler);
        });
        
        httpSecurity.logout((logout) -> {
            logout.logoutUrl("/logout")
                .addLogoutHandler(myLogoutHandler)
                .logoutSuccessHandler(myLogoutSuccessHandler);
        });
        
        httpSecurity.rememberMe()
            .rememberMeParameter("remember");
        
        httpSecurity.sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.cors(AbstractHttpConfigurer::disable);
        
        httpSecurity.authenticationManager(authenticationManager);
        
        httpSecurity.exceptionHandling((exception) -> {
            exception.authenticationEntryPoint(myAuthenticationEntryPoint)
                .accessDeniedHandler(myAccessDeniedHandler);
        });
        
        httpSecurity.addFilterBefore(myTokenFilter, UsernamePasswordAuthenticationFilter.class);
        
        return httpSecurity.build();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(provider);
    }
    
    @Bean
    public UserDetailsService userDetailsService() {
        return (username) -> {
            User user = Db.lambdaQuery(User.class)
                .eq(User::getUsername, username)
                .one();
            if (user == null) {
                throw new UsernameNotFoundException("username is not found");
            }
            
            List<RoleAuth> roleAuthList = Db.lambdaQuery(RoleAuth.class)
                .eq(RoleAuth::getRoleId, user.getRoleId())
                .select(RoleAuth::getAuthId)
                .list();
            
            List<Integer> authIdList = roleAuthList.stream().map(RoleAuth::getAuthId).toList();
            
            List<Auth> authList = Db.lambdaQuery(Auth.class)
                .in(Auth::getId, authIdList)
                .list();
            
            user.setAuthNameList(authList.stream().map(Auth::getName).toList());
            
            return user;
        };
    }
}
