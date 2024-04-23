package dev.kkkkkksssssaaaa.books.springsecurityinaction.domain.product.entity

import jakarta.persistence.*
import org.springframework.data.domain.Persistable

@Entity(name = "`product`")
class Product(
    name: String,
    price: String,
    currency: ProductCurrency,
    id: Long? = null
): Persistable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long? = id

    @Column(name = "`name`")
    var name: String = name
        protected set

    @Column(name = "`price`")
    var price: String = price
        protected set

    @Column(name = "`currency`")
    @Enumerated(EnumType.STRING)
    var currency: ProductCurrency = currency
        protected set

    override fun getId(): Long? {
        return this.id
    }

    override fun isNew(): Boolean {
        return this.id == null
    }
}