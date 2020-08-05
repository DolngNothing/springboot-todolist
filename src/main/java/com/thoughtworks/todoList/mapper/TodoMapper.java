package com.thoughtworks.todoList.mapper;


import com.thoughtworks.todoList.dto.TodoRequest;
import com.thoughtworks.todoList.dto.TodoResponse;
import com.thoughtworks.todoList.model.Todo;
import org.springframework.beans.BeanUtils;

public class TodoMapper {
    public static TodoResponse map(Todo todo){
        TodoResponse todoResponse=new TodoResponse();
        BeanUtils.copyProperties(todo,todoResponse);
        return todoResponse;
    }

    public static Todo map(TodoRequest todoRequest){
        Todo todo=new Todo();
        BeanUtils.copyProperties(todoRequest,todo);
        return todo;
    }
}
