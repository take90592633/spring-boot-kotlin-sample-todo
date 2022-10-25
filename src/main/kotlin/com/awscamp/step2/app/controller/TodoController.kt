package com.awscamp.step2.app.controller

import com.awscamp.step2.app.service.TodoService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class TodoController(
    private val todoService: TodoService
) {
    @GetMapping("/todo")
    fun index(
        model: Model
    ): String {
        // TODO todoをDBから取得する
//        val todoDtoList = listOf(
//            TodoDto(id = 1, text = "テスト1"),
//            TodoDto(id = 2, text = "テスト2"),
//        )
        val todoDtoList = todoService.findTodoAll()
        model.addAttribute("todoDtoList", todoDtoList)
        return "todo"
    }
}
