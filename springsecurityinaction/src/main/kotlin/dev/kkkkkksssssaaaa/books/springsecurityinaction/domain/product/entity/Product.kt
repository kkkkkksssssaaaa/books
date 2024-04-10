package dev.kkkkkksssssaaaa.books.springsecurityinaction.domain.product.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.springframework.data.domain.Persistable

@Entity(name = "`product`")
class Product(
    name: String,
    price: String,
    currency: String,
    id: Long? = null
): Persistable<Long> {
    @Id
    private var id: Long? = id

    @Column(name = "`name`")
    var name: String = name
        protected set

    @Column(name = "`price`")
    var price: String = price
        protected set

    @Column(name = "`currency`")
    var currency: String = currency
        protected set

    override fun getId(): Long? {
        return this.id
    }

    override fun isNew(): Boolean {
        return this.id == null
    }
}