package com.mercadolivro.enum

enum class Errors(
    val code: String,
    val message: String
) {
    ML0001("ML-0001", "Livro [%s] não existe"),
    ML0002("ML-0002", "Cliente [%s] não existe"),
    ML0003("ML-0003", "Não é possível alterar livro com status [%s]")
}