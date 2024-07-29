package com.kaleksandra.remoteplay.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource

@Configuration
@PropertySource("application.properties")
class TestConfig {
    /** Используется для запуска приложения в тестовом режиме **/
    @Value("\${test.token}")
    var testToken: String? = null

    /** Id пользователя для теста **/
    @Value("\${test.id}")
    var testId: Long = 0
}