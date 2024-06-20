package com.mercadolivro.extension

import com.mercadolivro.controller.request.PostBookRequest
import com.mercadolivro.controller.request.PostCustomerRequest
import com.mercadolivro.controller.request.PutBookRequest
import com.mercadolivro.controller.request.PutCustomerRequest
import com.mercadolivro.entities.BookEntity
import com.mercadolivro.entities.CustomerEntity
import com.mercadolivro.enum.BookStatus
import com.mercadolivro.enum.CustomerStatus

fun PostCustomerRequest.toCustomerEntity(): CustomerEntity{
    return CustomerEntity(
        name = this.name,
        email = this.email,
        status = CustomerStatus.ATIVO)
}

fun PutCustomerRequest.toCustomerEntity(customer: CustomerEntity): CustomerEntity{
    return CustomerEntity(
        id = customer.id ,
        name = this.name,
        email = this.email,
        status = customer.status)
}

fun PostBookRequest.toBookEntity(customer: CustomerEntity): BookEntity {
    return BookEntity(
        name = this.name,
        price = this.price,
        status = BookStatus.ATIVO,
        customer = customer
    )
}

fun PutBookRequest.toBookEntity(book: BookEntity): BookEntity{
    return BookEntity(
        id = book.id,
        name = this.name ?: book.name,
        price = this.price ?: book.price,
        status = book.status,
        customer = book.customer
    )
}