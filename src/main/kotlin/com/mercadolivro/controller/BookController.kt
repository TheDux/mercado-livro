package com.mercadolivro.controller

import com.mercadolivro.service.BookService
import com.mercadolivro.controller.request.PostBookRequest
import com.mercadolivro.controller.request.PutBookRequest
import com.mercadolivro.controller.response.BookResponse
import com.mercadolivro.extension.toBookEntity
import com.mercadolivro.extension.toResponse
import com.mercadolivro.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/books")
class BookController(
    val bookService: BookService,
    val customerService: CustomerService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun cadastrarLivro(
        @RequestBody request: PostBookRequest
    ){
        //TODO ATUALIZAR PARA ACEITAR CRIAR LIVROS SEM CUSTOMER RELACIONADO
        val customer = customerService.buscarClienteEspecifico(request.customerId)
        bookService.cadastrarLivro(request.toBookEntity(customer))
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun buscarTodosLivros(): List<BookResponse> {
        return bookService.buscarTodosLivros().map{ it.toResponse() }
    }

    @GetMapping("/actives")
    @ResponseStatus(HttpStatus.OK)
    fun buscarLivrosAtivos(): List<BookResponse> {
        return bookService.buscarLivrosAtivos().map { it.toResponse() }
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun buscarLivroEspecifico(
        @PathVariable id: Int): BookResponse{
        return bookService.buscarLivroEspecifico(id).toResponse()
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun apagarLivro(
        @PathVariable id: Int){
        bookService.apagarLivro(id)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun atualizarLivro(
        @PathVariable id: Int, @RequestBody book: PutBookRequest
    ){
        val bookSaved = bookService.buscarLivroEspecifico(id)
        bookService.atualizarLivro(book.toBookEntity(bookSaved))
    }
}