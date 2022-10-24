package com.awscamp.step2.app.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class TodoController {
    @GetMapping("/")
    fun index(
        model: Model
    ): String {
        model.addAttribute("message", "Hello World!!!")
        return "todo"
    }
}
