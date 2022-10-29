package com.awscamp.step2.app.controller

import com.awscamp.step2.app.service.AwsSesService
import com.awscamp.step2.app.service.TodoService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/todo")
class TodoController(
    private val todoService: TodoService,
    private val awsSesService: AwsSesService
) {
    @Value("\${aws.s3.bucket}")
    private val awsS3Bucket: String = ""

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
        awsSesService.sendSes(
            subject = "TODO登録",
            bodyText = "新しくTODOを登録しました。\n『$text」"
        )

        return todoList(model = model)
    }

    @PostMapping("/delete")
    fun deleteTodo(
        model: Model,
        @RequestParam("id") id: Long
    ): String {
        val todoDto = todoService.findTodoById(id = id)

        if (todoDto != null) {
            todoService.deleteTodo(id = id)
            awsSesService.sendSes(
                subject = "TODO削除",
                bodyText = "TODOを削除しました。\n「${todoDto.text}」"
            )
        }

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
