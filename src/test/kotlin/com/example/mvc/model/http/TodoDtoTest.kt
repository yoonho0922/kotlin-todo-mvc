package com.example.mvc.model.http

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import javax.validation.Validation

internal class TodoDtoTest{
    val validator = Validation.buildDefaultValidatorFactory().validator

    @Test
    fun todoDtoTest(){
        val todoDto = TodoDto().apply{
            this.title = "테스트"
            this.description = ""
            this.schedule = "2020-10-20 13:00:00"
        }

        val result = validator.validate(todoDto)

        result.forEach{
            println(it.propertyPath)
            println(it.message)
            println(it.invalidValue)
        }

        Assertions.assertEquals(true, result.isEmpty())
    }
}