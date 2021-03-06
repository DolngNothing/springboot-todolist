package com.thoughtworks.todoList.controller;

import com.thoughtworks.todoList.dto.TodoRequest;
import com.thoughtworks.todoList.dto.TodoResponse;
import com.thoughtworks.todoList.exception.IllegalOperationException;
import com.thoughtworks.todoList.exception.NoSuchDataException;
import com.thoughtworks.todoList.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/todos")
public class TodoController {
    @Autowired
    TodoService todoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TodoResponse saveTodo(@RequestBody TodoRequest todoRequest){
        return todoService.save(todoRequest);
    }

    @GetMapping
    public List<TodoResponse> getAll(){
        return todoService.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id){
        todoService.deleteById(id);
    }

    @PutMapping("/{id}")
    public TodoResponse update(@PathVariable Integer id,@RequestBody TodoRequest todoRequest) throws IllegalOperationException, NoSuchDataException {
        return todoService.update(id,todoRequest);
    }

}
