package com.thoughtworks.todoList.service;

import com.thoughtworks.todoList.dto.TodoResponse;
import com.thoughtworks.todoList.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {


    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository=todoRepository;
    }

    public List<TodoResponse> findAll() {
        return null;
    }
}
