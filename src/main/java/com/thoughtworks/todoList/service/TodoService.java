package com.thoughtworks.todoList.service;

import com.thoughtworks.todoList.dto.TodoRequest;
import com.thoughtworks.todoList.dto.TodoResponse;
import com.thoughtworks.todoList.exception.IllegalOperationException;
import com.thoughtworks.todoList.exception.NoSuchDataException;
import com.thoughtworks.todoList.mapper.TodoMapper;
import com.thoughtworks.todoList.model.Todo;
import com.thoughtworks.todoList.repository.TodoRepository;
import org.springframework.beans.BeanUtils;
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

    public TodoResponse save(TodoRequest todoRequest) {
        return TodoMapper.map(todoRepository.save(TodoMapper.map(todoRequest)));
    }

    public TodoResponse update(int i, TodoRequest todoRequest) throws NoSuchDataException, IllegalOperationException {
        Todo todo = todoRepository.findById(i).orElse(null);
        if(todo==null) throw new NoSuchDataException();
        if(!todo.getId().equals(todoRequest.getId())) throw new IllegalOperationException();
        BeanUtils.copyProperties(todoRequest,todo);
        return TodoMapper.map(todoRepository.save(todo));
    }

    public void deleteById(Integer id) {
        todoRepository.deleteById(id);
    }
}
