package com.debug.kill.server.config;

import com.debug.kill.server.service.CustomRealm;
import org.apache.commons.collections.map.HashedMap;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @author User
 * @Title: ShiroConfig
 * @ProjectName kill
 * @Description: Shiro通用化配置
 * @date 2020/6/30 18:13
 */
@Configuration
public class ShiroConfig {

    @Bean
    public CustomRealm customRealm(){
        return new CustomRealm();
    }

    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(customRealm());
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager());
        bean.setLoginUrl("/to/login");
        bean.setUnauthorizedUrl("/unauth");

        Map<String,String>  filterChainDefinitionMap= new HashedMap();
        filterChainDefinitionMap.put("/to/login","anon");
        filterChainDefinitionMap.put("/item/detail/*","authc");
        filterChainDefinitionMap.put("/kill/execute","authc");
        filterChainDefinitionMap.put("/**","anon");

        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return bean;
    }
}
