package com.example.mvc.service

import com.example.mvc.database.Todo
import com.example.mvc.database.convertTodo
import com.example.mvc.model.http.TodoDto
import com.example.mvc.model.http.convertTodoDto
import com.example.mvc.repository.TodoRepositoryImpl
import org.springframework.stereotype.Service

@Service
class TodoService(
    val todoRepositoryImpl: TodoRepositoryImpl
) {

    fun create(todoDto: TodoDto): TodoDto? {
        return todoDto.let {
            Todo().convertTodo(it)
        }.let {
            todoRepositoryImpl.save(it)
        }?.let {
            TodoDto().convertTodoDto(it)
        }
    }

    fun read(index: Int): TodoDto? {
        return todoRepositoryImpl.findOne(index)?.let {
            TodoDto().convertTodoDto(it)
        }

    }

    fun readAll(): MutableList<TodoDto> {
        return todoRepositoryImpl.findAll().map {
            TodoDto().convertTodoDto(it)
            }.toMutableList()
    }

    fun update(todoDto: TodoDto): TodoDto? {
        return todoDto.let {
            Todo().convertTodo(it)
        }.let {
            todoRepositoryImpl.save(it)
        }?.let {
            TodoDto().convertTodoDto(it)
        }
    }

    fun delete(index: Int): Boolean {
        return todoRepositoryImpl.delete(index)
    }
}