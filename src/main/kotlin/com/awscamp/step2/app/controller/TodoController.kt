package com.awscamp.step2.app.controller

import com.awscamp.step2.app.service.TodoService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/todo")
class TodoController(
    private val todoService: TodoService
) {
    @GetMapping("/list")
    fun todo(
        model: Model
    ): String {
        return todoList(model = model)
    }

    @PostMapping("/create")
    fun createTodo(
        model: Model,
        @RequestParam("text") text: String
    ): String {
        todoService.createTodo(text = text)

        return todoList(model = model)
    }

    // TODO 削除処理(削除 → 表示)
    @PostMapping("/delete")
    fun deleteTodo(
        model: Model,
        @RequestParam("id") id: Long
    ): String {
        todoService.deleteTodo(id = id)

        return todoList(model = model)
    }

    private fun todoList(
        model: Model
    ): String {
        val todoDtoList = todoService.findTodoAll()
        model.addAttribute("todoDtoList", todoDtoList)
        return "todo"
    }
}
