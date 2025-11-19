package com.easyhooon.metroapplication.core.data.impl.repository

import com.easyhooon.metroapplication.core.data.api.repository.BookRepository
import com.easyhooon.metroapplication.core.di.DataScope
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.SingleIn

@SingleIn(DataScope::class)
@Inject
class DefaultBookRepository : BookRepository {
    override suspend fun getBookList(): Result<List<String>> {
        return Result.success(listOf("Book 1", "Book 2", "Book 3"))
    }

    override suspend fun getBookDetail(id: String): Result<String> {
        return Result.success("Book Detail for $id")
    }

    override suspend fun addBook(title: String): Result<Unit> {
        return Result.success(Unit)
    }
}
