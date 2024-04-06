package dev.kkkkkksssssaaaa.books.springsecurityinaction.config

import org.springframework.beans.factory.InitializingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.security.core.context.SecurityContextHolder

@Configuration
@EnableAsync
class EnableAsyncConfiguration {
    @Bean
    fun initializingBean(): InitializingBean {
        return InitializingBean {
            SecurityContextHolder.setStrategyName(
                SecurityContextHolder.MODE_INHERITABLETHREADLOCAL
            )
        }
    }
}