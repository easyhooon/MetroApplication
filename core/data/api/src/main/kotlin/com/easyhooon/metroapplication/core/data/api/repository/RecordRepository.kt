package com.easyhooon.metroapplication.core.data.api.repository

interface RecordRepository {
    suspend fun getRecordList(): Result<List<String>>
    suspend fun addRecord(content: String): Result<Unit>
    suspend fun deleteRecord(id: String): Result<Unit>
}
