package com.mercadolivro.service

import com.mercadolivro.entities.BookEntity
import com.mercadolivro.entities.CustomerEntity
import com.mercadolivro.enum.BookStatus
import com.mercadolivro.repository.BookRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class BookService(
    val bookRepository: BookRepository
) {
    fun cadastrarLivro(book: BookEntity) {
        bookRepository.save(book)
    }

    fun buscarTodosLivros(pageable: Pageable): Page<BookEntity> {
        return bookRepository.findAll(pageable)
    }

    fun buscarLivrosAtivos(pageable: Pageable): Page<BookEntity> {
        return bookRepository.findByStatus(BookStatus.ATIVO, pageable)
    }

    fun buscarLivroEspecifico(id: Int): BookEntity {
        return bookRepository.findById(id).orElseThrow()
    }

    fun apagarLivro(id: Int) {
        val book = bookRepository.findById(id).orElseThrow()
        book.status = BookStatus.CANCELADO

        atualizarLivro(book)
    }

    fun atualizarLivro(book: BookEntity) {
        bookRepository.save(book)
    }

    fun deleteByCustomer(customer: CustomerEntity) {
        val books = bookRepository.findByCustomer(customer)

        for(book in books){
            book.status = BookStatus.DELETADO
        }
        bookRepository.saveAll(books)
    }
}
