package dev.kkkkkksssssaaaa.books.springsecurityinaction.domain.user.service

import org.springframework.security.concurrent.DelegatingSecurityContextCallable
import org.springframework.security.concurrent.DelegatingSecurityContextExecutorService
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Service
class CurrentUserService {
    fun name(): String {
        val authentication = SecurityContextHolder.getContext().authentication

        return authentication.name
    }

    fun ciao(): String {
        val task: Callable<String> = Callable<String> {
            this.name()
        }

        val executor: ExecutorService = Executors.newCachedThreadPool()

        try {
            val contextTask = DelegatingSecurityContextCallable(task)

            return executor.submit(contextTask).get()
        } finally {
            executor.shutdown()
        }
    }

    fun hola(): String {
        val task: Callable<String> = Callable<String> {
            SecurityContextHolder.getContext().authentication.name
        }

        val executor: ExecutorService = Executors.newCachedThreadPool()
        val securityExecutor = DelegatingSecurityContextExecutorService(executor)

        try {
            return securityExecutor.submit(task).get()
        } finally {
            securityExecutor.shutdown()
            executor.shutdown()
        }
    }
}