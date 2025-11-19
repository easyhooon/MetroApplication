package com.easyhooon.metroapplication.core.data.api.repository

interface BookRepository {
    suspend fun getBookList(): Result<List<String>>
    suspend fun getBookDetail(id: String): Result<String>
    suspend fun addBook(title: String): Result<Unit>
}
