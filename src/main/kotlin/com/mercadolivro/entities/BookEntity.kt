package com.mercadolivro.entities

import com.mercadolivro.enum.BookStatus
import jakarta.persistence.*
import java.math.BigDecimal

@Entity(name = "book")
data class BookEntity (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column
    var name: String,

    @Column
    var price: BigDecimal,

    @Column
    @Enumerated(EnumType.STRING)
    var status: BookStatus? = null,

    @ManyToOne
    @JoinColumn(name = "customer_id")
    var customer: CustomerEntity? = null,
)