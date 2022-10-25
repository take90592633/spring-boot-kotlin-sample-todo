package com.awscamp.step2.app.service

import com.awscamp.step2.app.dto.TodoDto
import com.awscamp.step2.app.repository.TodoRepository
import org.springframework.stereotype.Service

@Service
class TodoService(
    private val todoRepository: TodoRepository
) {

    fun findTodoAll(): List<TodoDto> {
        return todoRepository.findTodoAll().map { TodoDto(it) }
    }

    fun createTodo(
        text: String
    ) {
        todoRepository.createTodo(text = text)
    }

    fun deleteTodo(
        id: Long
    ) {
        todoRepository.deleteTodo(id = id)
    }
}
