package com.mercadolivro.entities

import com.mercadolivro.enum.BookStatus
import com.mercadolivro.enum.Errors
import com.mercadolivro.exception.BadRequestException
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

    @ManyToOne
    @JoinColumn(name = "customer_id")
    var customer: CustomerEntity? = null,
){
    @Column
    @Enumerated(EnumType.STRING)
    var status: BookStatus? = null
        set(value){
            if(field == BookStatus.CANCELADO || field == BookStatus.DELETADO){
                throw BadRequestException(
                    Errors.ML0003.message.format(field),
                    Errors.ML0003.code
                )
            }
            field = value
        }

    constructor(
        id: Int? = null,
        name: String,
        price: BigDecimal,
        customer: CustomerEntity? = null,
        status: BookStatus?
    ): this(id,name,price,customer){
        this.status = status
    }
}