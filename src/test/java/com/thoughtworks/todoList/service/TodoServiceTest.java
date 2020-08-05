package com.thoughtworks.todoList.service;

import com.thoughtworks.todoList.dto.TodoResponse;
import com.thoughtworks.todoList.mapper.TodoMapper;
import com.thoughtworks.todoList.model.Todo;
import com.thoughtworks.todoList.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;


public class TodoServiceTest {
    @Test
    void should_get_all_todo_list_when_get_given() {
        //given
        TodoRepository todoRepository = Mockito.mock(TodoRepository.class);
        TodoService todoService = new TodoService(todoRepository);
        List<Todo> todos = Collections.singletonList(new Todo());
        given(todoRepository.findAll()).willReturn(todos);

        //when
        List<TodoResponse> foundTodoList = todoService.findAll();

        //then
        assertEquals(todos.stream().map(TodoMapper::map).collect(Collectors.toList()), foundTodoList);
    }
}
