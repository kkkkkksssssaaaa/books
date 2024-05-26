package dev.kkkkkksssssaaaa.books.springsecurityinaction.grant.common.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint


@Configuration
@EnableWebSecurity
class AuthServerConfiguration {
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    fun authorizationServerSecurityFilterChain(http: HttpSecurity): SecurityFilterChain {
        // @formatter:off
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http)
        http.getConfigurer(OAuth2AuthorizationServerConfigurer::class.java).oidc(Customizer.withDefaults()) // Enable OpenID Connect 1.0
        http
            .exceptionHandling{exceptions:ExceptionHandlingConfigurer<HttpSecurity?> -> exceptions
                .authenticationEntryPoint(LoginUrlAuthenticationEntryPoint("/login"))}
            .oauth2ResourceServer{resourceServer:OAuth2ResourceServerConfigurer<HttpSecurity?> -> resourceServer.jwt(Customizer.withDefaults())}
        // @formatter:on

        return http.build()
    }
}