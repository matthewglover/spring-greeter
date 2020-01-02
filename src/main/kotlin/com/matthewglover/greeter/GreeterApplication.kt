package com.matthewglover.greeter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GreeterApplication

fun main(args: Array<String>) {
    runApplication<GreeterApplication>(*args)
}
