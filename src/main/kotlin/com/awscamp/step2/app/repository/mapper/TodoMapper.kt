package com.awscamp.step2.app.repository.mapper

import com.awscamp.step2.app.repository.entity.Todo
import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.Insert
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

    @Select(
        """
            SELECT
                id,
                text
            FROM
                todo
            WHERE
                id = #{id}
        """
    )
    fun findTodoById(
        id: Long
    ): Todo?

    @Insert(
        """
            INSERT INTO todo (text)
            VALUES (#{text})
        """
    )
    fun createTodo(
        text: String
    ): Int

    @Delete(
        """
            DELETE FROM todo
            WHERE id = #{id}
        """
    )
    fun deleteTodo(
        id: Long
    ): Int
}
