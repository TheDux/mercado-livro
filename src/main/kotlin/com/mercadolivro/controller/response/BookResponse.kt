package com.mercadolivro.controller.response

import com.mercadolivro.entities.CustomerEntity
import com.mercadolivro.enum.BookStatus
import java.math.BigDecimal

data class BookResponse (

    var id: Int? = null,

    var name: String,

    var price: BigDecimal,

    var customer: CustomerEntity? = null,

    var status: BookStatus? = null
)