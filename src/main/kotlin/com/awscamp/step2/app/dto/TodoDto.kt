package com.awscamp.step2.app.dto

import com.awscamp.step2.app.repository.entity.Todo

data class TodoDto(
    val id: Int,
    val text: String
) {
    constructor(todo: Todo) : this(
        id = todo.id,
        text = todo.text
    )
}
