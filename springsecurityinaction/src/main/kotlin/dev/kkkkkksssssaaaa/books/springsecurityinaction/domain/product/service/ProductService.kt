package dev.kkkkkksssssaaaa.books.springsecurityinaction.domain.product.service

import dev.kkkkkksssssaaaa.books.springsecurityinaction.domain.product.entity.Product
import dev.kkkkkksssssaaaa.books.springsecurityinaction.domain.product.entity.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository
) {
    fun findAll(): List<Product> {
        return productRepository.findAll()
    }
}