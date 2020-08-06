package com.thoughtworks.todoList.controller;

import com.thoughtworks.todoList.dto.TodoRequest;
import com.thoughtworks.todoList.dto.TodoResponse;
import com.thoughtworks.todoList.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todos")
public class TodoController {
    @Autowired
    TodoService todoService;

    @PostMapping
    public TodoResponse saveTodo(@RequestBody TodoRequest todoRequest){
        return todoService.save(todoRequest);
    }


}
