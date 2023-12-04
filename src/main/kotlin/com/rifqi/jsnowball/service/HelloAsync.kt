package com.rifqi.jsnowball.service

import org.springframework.scheduling.annotation.Async
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit

@Service
class HelloAsync {
    @Bean
    fun log(): Logger {
        return LoggerFactory.getLogger(HelloAsync::class.java)
    }

    @Async
    fun helloWorld (){
        TimeUnit.SECONDS.sleep(2)
        log().info("hello after 2 seconds {}",Thread.currentThread())
    }
}