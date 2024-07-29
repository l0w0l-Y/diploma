package com.kaleksandra.remoteplay

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}