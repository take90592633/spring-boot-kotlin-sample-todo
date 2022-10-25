package com.awscamp.step2.app.repository

import com.awscamp.step2.app.repository.entity.Todo
import com.awscamp.step2.app.repository.mapper.TodoMapper
import org.springframework.stereotype.Repository

@Repository
class TodoRepository(
    private val todoMapper: TodoMapper
) {

    fun findTodoAll(): List<Todo> {
        return todoMapper.findTodoAll()
    }

    fun createTodo(
        text: String
    ): Int {
        return todoMapper.createTodo(text = text)
    }

    fun deleteTodo(
        id: Long
    ): Int {
        return todoMapper.deleteTodo(id = id)
    }
}
