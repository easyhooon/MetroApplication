package com.easyhooon.metroapplication

import com.easyhooon.metroapplication.core.data.api.repository.UserRepository
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.SingleIn

/**
 * Simple presenter to verify that Repository injection works fine.
 *
 * This proves that the Metro bug is NOT related to Repository injection itself,
 * but specifically to Repository → DataSource dependency chain.
 */
@SingleIn(AppScope::class)
@Inject
class MainPresenter(
    private val userRepository: UserRepository,
) {
    suspend fun loadUserProfile(): String {
        return userRepository.getUserProfile().getOrElse { "Error loading profile" }
    }
}
