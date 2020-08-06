package com.thoughtworks.todoList.service;

import com.thoughtworks.todoList.dto.TodoRequest;
import com.thoughtworks.todoList.dto.TodoResponse;
import com.thoughtworks.todoList.exception.IllegalOperationException;
import com.thoughtworks.todoList.exception.NoSuchDataException;
import com.thoughtworks.todoList.mapper.TodoMapper;
import com.thoughtworks.todoList.model.Todo;
import com.thoughtworks.todoList.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


public class TodoServiceTest {
    @Test
    void should_get_all_todo_list_when_get_given() {
        //given
        TodoRepository todoRepository = Mockito.mock(TodoRepository.class);
        TodoService todoService = new TodoService(todoRepository);
        List<Todo> todos = Collections.singletonList(new Todo(1,"todo",false));
        given(todoRepository.findAll()).willReturn(todos);

        //when
        List<TodoResponse> foundTodoList = todoService.findAll();

        //then
        assertEquals(todos.stream().map(TodoMapper::map).collect(Collectors.toList()), foundTodoList);
    }

    @Test
    void should_return_saved_todo_when_save_given_new_todo() {
        //given
        TodoRepository todoRepository = Mockito.mock(TodoRepository.class);
        TodoService todoService = new TodoService(todoRepository);
        TodoRequest todoRequest=new TodoRequest(1,"todo",false);
        given(todoRepository.save(any(Todo.class))).willReturn(TodoMapper.map(todoRequest));

        //when
        TodoResponse savedTodo = todoService.save(todoRequest);

        //then
        assertEquals(TodoMapper.map(TodoMapper.map(todoRequest)), savedTodo);
    }

    @Test
    void should_return_new_todo_when_update_given_new_todo_todo_id() throws IllegalOperationException, NoSuchDataException {
        //given
        TodoRepository todoRepository = Mockito.mock(TodoRepository.class);
        TodoService todoService = new TodoService(todoRepository);
        TodoRequest todoRequest=new TodoRequest(1,"todo",false);
        given(todoRepository.findById(anyInt())).willReturn(Optional.of(TodoMapper.map(todoRequest)));
        given(todoRepository.save(any(Todo.class))).willReturn(TodoMapper.map(todoRequest));
        //when
        TodoResponse newTodo = todoService.update(1,todoRequest);

        //then
        assertEquals(TodoMapper.map(TodoMapper.map(todoRequest)), newTodo);
    }

    @Test
    void should_return_void_todo_when_delete_given_todo_id() throws IllegalOperationException, NoSuchDataException {
        //given
        TodoRepository todoRepository = Mockito.mock(TodoRepository.class);
        TodoService todoService = new TodoService(todoRepository);
        TodoRequest todoRequest=new TodoRequest(1,"todo",false);
        given(todoRepository.findById(anyInt())).willReturn(Optional.of(TodoMapper.map(todoRequest)));
        //when
        todoService.deleteById(1);

        //then
        verify(todoRepository).deleteById(1);
    }
}
