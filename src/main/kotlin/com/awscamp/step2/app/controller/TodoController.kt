package com.awscamp.step2.app.controller

import com.awscamp.step2.app.service.TodoService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/todo")
class TodoController(
    private val todoService: TodoService
) {
    @GetMapping("/list")
    fun todo(
        model: Model
    ): String {
        showList(model = model)
        return "todo"
    }

    // TODO 登録処理(登録 → 表示)
    fun createTodo(
        text: String
    ) {
        todoService.createTodo(text = text)
    }

    // TODO 削除処理(削除 → 表示)
    fun deleteTodo(
        id: Long
    ) {
        todoService.deleteTodo(id = id)
    }

    private fun showList(
        model: Model
    ){
        val todoDtoList = todoService.findTodoAll()
        model.addAttribute("todoDtoList", todoDtoList)
    }
}
