//package com.rifqi.jsnowball.configurations
//
//import org.slf4j.Logger
//import org.slf4j.LoggerFactory
//import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.context.annotation.Primary
//import org.springframework.core.task.TaskExecutor
//import org.springframework.scheduling.annotation.AsyncConfigurer
//import org.springframework.scheduling.annotation.EnableAsync
//import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
//import java.lang.reflect.Method
//import java.util.concurrent.Executor
//import java.util.concurrent.ThreadPoolExecutor
//
//
//@EnableAsync
//@Configuration
//class AsyncConfig : AsyncConfigurer {
//    private val log: Logger = LoggerFactory.getLogger(this.javaClass)
//    @Bean
//    override fun getAsyncExecutor(): Executor {
//        val taskExecutor = ThreadPoolTaskExecutor()
//        taskExecutor.corePoolSize = 5
//        taskExecutor.maxPoolSize = 10
//        taskExecutor.setQueueCapacity(25)
//        taskExecutor.initialize()
//        return taskExecutor
//    }
//
//    @Bean("asyncHello")
//    fun asyncHello(): Executor {
//        val executor = ThreadPoolTaskExecutor()
//        executor.corePoolSize = 4
//        executor.maxPoolSize = 8
//        executor.setQueueCapacity(100)
//        executor.setThreadNamePrefix("AsyncThread-")
//        executor.initialize()
//        return executor
//    }
//
//    @Bean("asyncMasterExecutor")
//    fun asyncMasterExecutor(): TaskExecutor {
//        val threadPoolExecutor = ThreadPoolExecutor.CallerRunsPolicy()
//        val executor = ThreadPoolTaskExecutor()
//        executor.corePoolSize = 10
//        executor.maxPoolSize = 8
//        executor.setQueueCapacity(100)
//        executor.setRejectedExecutionHandler(threadPoolExecutor)
//        executor.setThreadNamePrefix("AsyncThreadMaster-")
//        executor.setWaitForTasksToCompleteOnShutdown(true)
//        executor.initialize()
//        return executor
//    }
//
//
//    override fun getAsyncUncaughtExceptionHandler(): AsyncUncaughtExceptionHandler {
//        return MyAsyncUncaughtExceptionHandler()
//    }
//
//    inner class MyAsyncUncaughtExceptionHandler : AsyncUncaughtExceptionHandler {
//        override fun handleUncaughtException(
//            throwable: Throwable, method: Method, vararg params: Any?) {
//            log.error("Uncaught exception in asynchronous method", throwable)
//        }
//    }
//}
//sd ja
