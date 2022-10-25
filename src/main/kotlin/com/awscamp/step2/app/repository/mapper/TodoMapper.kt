package com.awscamp.step2.app.repository.mapper

import com.awscamp.step2.app.repository.entity.Todo
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

@Mapper
interface TodoMapper {

    @Select(
        """
            SELECT
                id,
                text
            FROM
                todo
        """
    )
    fun findTodoAll(): List<Todo>
}
