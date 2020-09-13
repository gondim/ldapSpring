package com.atlantico.prova.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.ldap.repository.config.EnableLdapRepositories;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

@Configuration
@EnableLdapRepositories
public class LdapSecurity  {

	@Value("${PATHLDAP:localhost}")
	private String path;
	
    @Bean
    LdapContextSource contextSource() {
    	
        LdapContextSource ldapContextSource = new LdapContextSource();
        ldapContextSource.setUrl("ldap://"+ path +":389");
        ldapContextSource.setBase("dc=techinterview,dc=com");
        ldapContextSource.setUserDn("cn=admin,dc=techinterview,dc=com");
        ldapContextSource.setPassword("123456");
        return ldapContextSource;
    }

    @Bean
    public LdapTemplate ldapTemplate() {
        return new LdapTemplate(contextSource());
    }
    
}
