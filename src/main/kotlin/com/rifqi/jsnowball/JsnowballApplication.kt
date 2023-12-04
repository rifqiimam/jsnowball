package com.rifqi.jsnowball

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@EnableAsync
@SpringBootApplication
class JsnowballApplication

@Override
fun main(args: Array<String>) {
	runApplication<JsnowballApplication>(*args)
}


