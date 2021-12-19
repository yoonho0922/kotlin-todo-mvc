package com.example.mvc.config

import com.example.mvc.database.TodoDatabase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppConfig {

    @Bean(initMethod = "init")
    fun todoDatabase(): TodoDatabase{
        return TodoDatabase()
    }
}