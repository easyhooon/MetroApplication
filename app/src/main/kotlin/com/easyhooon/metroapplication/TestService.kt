package com.easyhooon.metroapplication

import com.easyhooon.metroapplication.core.data.api.repository.UserRepository
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.SingleIn

@SingleIn(AppScope::class)
@Inject
class TestClass(
    private val userRepository: UserRepository,
)
