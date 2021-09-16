package com.p5.adoptions.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@Configuration
@EnableGlobalMethodSecurity (securedEnabled = true, prePostEnabled = true)
public class AdoptionRoleHierarchy extends GlobalMethodSecurityConfiguration
{

    @Bean
    public RoleHierarchy roleHierarchy(){
         RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
         roleHierarchy.setHierarchy(" ROLE_ADMIN > ROLE_MOD \n ROLE_MOD > ROLE_USER");
         return roleHierarchy;
    }

    protected MethodSecurityExpressionHandler createExpressionHandler(){
        DefaultMethodSecurityExpressionHandler handler = new DefaultMethodSecurityExpressionHandler();
        handler.setRoleHierarchy(roleHierarchy());
        return handler;
    }
}
