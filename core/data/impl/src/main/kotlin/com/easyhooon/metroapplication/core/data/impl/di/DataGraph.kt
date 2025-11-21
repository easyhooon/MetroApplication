package com.easyhooon.metroapplication.core.data.impl.di

import com.easyhooon.metroapplication.core.data.api.repository.UserRepository
import com.easyhooon.metroapplication.core.data.impl.repository.DefaultUserRepository
import com.easyhooon.metroapplication.core.di.DataScope
import dev.zacsweers.metro.Binds
import dev.zacsweers.metro.ContributesTo

@ContributesTo(DataScope::class)
interface DataGraph {

    @Binds
    val DefaultUserRepository.bind: UserRepository
}
