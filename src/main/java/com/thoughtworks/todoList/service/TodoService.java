package com.thoughtworks.todoList.service;

import com.thoughtworks.todoList.dto.TodoResponse;
import com.thoughtworks.todoList.mapper.TodoMapper;
import com.thoughtworks.todoList.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoService {


    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository=todoRepository;
    }

    public List<TodoResponse> findAll() {
        return todoRepository.findAll().stream().map(TodoMapper::map).collect(Collectors.toList());
    }
}
