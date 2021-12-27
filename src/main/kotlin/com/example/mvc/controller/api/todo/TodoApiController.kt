package com.example.mvc.controller.api.todo

import com.example.mvc.model.http.TodoDto
import com.example.mvc.service.TodoService
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("api/todo")
class TodoApiController (
    val todoService: TodoService
){

    @GetMapping(path = [""])
    fun read(@RequestParam(required = false) index: Int?): ResponseEntity<Any?> {

        return index?.let {
            todoService.read(it)
        }?.let {
            return ResponseEntity.ok(it)
        }?: kotlin.run{
            // 인덱스가 없으면 전체 조회
            return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY)
                .header(HttpHeaders.LOCATION, "/api/todo/all").build()
        }
    }

    @GetMapping(path = ["/all"])
    fun readAll(): MutableList<TodoDto> {
        return todoService.readAll()
    }

    @PostMapping(path = [""])
    fun create(@Valid @RequestBody todoDto: TodoDto): TodoDto? {
        return todoService.create(todoDto)
    }

    // TODO create = 201, update = 200 되도록
    @PutMapping(path = [""])
    fun update(@Valid @RequestBody todoDto: TodoDto): TodoDto? {
        return todoService.create(todoDto)
    }

    @DeleteMapping(path = ["/{index}"])
    fun delete(@PathVariable(name = "index") _index: Int): ResponseEntity<Any> {

        if(!todoService.delete(_index)){
            return ResponseEntity.status(500).build()
        }

        return ResponseEntity.ok().build()
    }

}