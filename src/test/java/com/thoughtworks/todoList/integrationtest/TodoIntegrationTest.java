package com.thoughtworks.todoList.integrationtest;

import com.thoughtworks.todoList.model.Todo;
import com.thoughtworks.todoList.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TodoIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    TodoRepository todoRepository;

    @Test
    void should_return_todo_when_post_given_new_todo() throws Exception {
        //given
        Todo save = todoRepository.save(new Todo(1, "6666", false));
        //when
        mockMvc.perform(get("/companies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(save.getId()))
                .andExpect(jsonPath("$.name").value("6666"));
        //then
    }


}
