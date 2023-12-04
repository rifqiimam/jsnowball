package com.rifqi.jsnowball

import com.rifqi.jsnowball.service.HelloAsync
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Bean
import java.util.concurrent.TimeUnit


@SpringBootTest
class HelloAsyncTest(
) {
    @Autowired
    private lateinit var helloAsync: HelloAsync
    @Bean
    fun log(): Logger {
        return LoggerFactory.getLogger(HelloAsync::class.java)
    }
    @Test
    fun hello() {
        for (i in 0..9) {
            helloAsync.helloWorld()
        }
        log().info("After calling hello()")
        TimeUnit.SECONDS.sleep(5)
    }
}
