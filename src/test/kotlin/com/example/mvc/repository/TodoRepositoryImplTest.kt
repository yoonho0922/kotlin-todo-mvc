package com.example.mvc.repository

import com.example.mvc.config.AppConfig
import com.example.mvc.database.Todo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.LocalDateTime

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [TodoRepositoryImpl::class, AppConfig::class])
internal class TodoRepositoryImplTest{

    @Autowired
    lateinit var todoRepositoryImpl: TodoRepositoryImpl

    @BeforeEach
    fun before(){
        todoRepositoryImpl.todoDatabase.init()
    }

    @Test
    fun saveTest(){
        val todo = Todo().apply {
            this.title = "테스트 일정"
            this.description = "테스트"
            this.schedule = LocalDateTime.now()
        }

        val result = todoRepositoryImpl.save(todo)

        assertEquals(1, result?.index)
        assertNotNull(result?.createdAt)
        assertNotNull(result?.updateAt)
        assertEquals("테스트 일정", result?.title)
        assertEquals("테스트", result?.description)
    }

    @Test
    fun saveAllTest(){
        val todoList = mutableListOf(
            Todo().apply {
                this.title = "테스트 일정"
                this.description = "테스트"
                this.schedule = LocalDateTime.now()
            },
            Todo().apply {
                this.title = "테스트 일정"
                this.description = "테스트"
                this.schedule = LocalDateTime.now()
            },
            Todo().apply {
                this.title = "테스트 일정"
                this.description = "테스트"
                this.schedule = LocalDateTime.now()
            }
        )

        val result = todoRepositoryImpl.saveAll(todoList)
        assertEquals(true, result)
    }

    @Test
    fun findOneTest(){
        todoRepositoryImpl.todoDatabase.init()

        val todoList = mutableListOf(
            Todo().apply {
                this.title = "테스트 일정1"
                this.description = "테스트"
                this.schedule = LocalDateTime.now()
            },
            Todo().apply {
                this.title = "테스트 일정2"
                this.description = "테스트"
                this.schedule = LocalDateTime.now()
            },
            Todo().apply {
                this.title = "테스트 일정3"
                this.description = "테스트"
                this.schedule = LocalDateTime.now()
            }
        )
        todoRepositoryImpl.saveAll(todoList)

        val result = todoRepositoryImpl.findOne(2)
        println(result)

        assertNotNull(result)
        assertEquals("테스트 일정2", result?.title)
    }

    @Test
    fun updateTest() {
        val todo = Todo().apply {
            this.title = "테스트 일정"
            this.description = "테스트"
            this.schedule = LocalDateTime.now()
        }
        val insertTodo = todoRepositoryImpl.save(todo)

        val newTodo = Todo().apply {
            this.index = insertTodo?.index
            this.title = "테스트 일정"
            this.description = "테스트"
            this.schedule = LocalDateTime.now()
        }
        val result = todoRepositoryImpl.save(newTodo)

        assertNotNull(result)
        assertEquals(insertTodo?.index, result?.index)
        assertEquals(insertTodo?.title, result?.title)
        assertEquals(insertTodo?.description, result?.description)
    }
}