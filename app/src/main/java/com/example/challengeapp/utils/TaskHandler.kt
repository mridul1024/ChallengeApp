package com.example.challengeapp.utils

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * Task handler class which is used in performing room database operations in different threads
 */
class TaskHandler {
    fun submitTask(task: Runnable){
        var executorService: ExecutorService = Executors.newFixedThreadPool(3)
        executorService.submit(task)
    }
}