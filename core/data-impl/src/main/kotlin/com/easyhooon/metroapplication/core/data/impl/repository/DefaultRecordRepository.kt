package com.easyhooon.metroapplication.core.data.impl.repository

import com.easyhooon.metroapplication.core.data.api.repository.RecordRepository
import com.easyhooon.metroapplication.core.di.DataScope
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.SingleIn

@SingleIn(DataScope::class)
@Inject
class DefaultRecordRepository : RecordRepository {
    override suspend fun getRecordList(): Result<List<String>> {
        return Result.success(listOf("Record 1", "Record 2"))
    }

    override suspend fun addRecord(content: String): Result<Unit> {
        return Result.success(Unit)
    }

    override suspend fun deleteRecord(id: String): Result<Unit> {
        return Result.success(Unit)
    }
}
