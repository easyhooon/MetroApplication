package com.easyhooon.metroapplication.core.data.impl.di

import com.easyhooon.metroapplication.core.data.api.repository.AuthRepository
import com.easyhooon.metroapplication.core.data.api.repository.BookRepository
import com.easyhooon.metroapplication.core.data.api.repository.RecordRepository
import com.easyhooon.metroapplication.core.data.api.repository.UserRepository
import com.easyhooon.metroapplication.core.data.impl.repository.DefaultAuthRepository
import com.easyhooon.metroapplication.core.data.impl.repository.DefaultBookRepository
import com.easyhooon.metroapplication.core.data.impl.repository.DefaultRecordRepository
import com.easyhooon.metroapplication.core.data.impl.repository.DefaultUserRepository
import com.easyhooon.metroapplication.core.di.DataScope
import dev.zacsweers.metro.Binds
import dev.zacsweers.metro.ContributesTo

@ContributesTo(DataScope::class)
interface DataGraph {

    @Binds
    val DefaultAuthRepository.bind: AuthRepository

    @Binds
    val DefaultBookRepository.bind: BookRepository

    @Binds
    val DefaultRecordRepository.bind: RecordRepository

    @Binds
    val DefaultUserRepository.bind: UserRepository
}