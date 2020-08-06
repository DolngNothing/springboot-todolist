package com.thoughtworks.todoList.integrationtest;

import com.thoughtworks.todoList.dto.TodoRequest;
import com.thoughtworks.todoList.model.Todo;
import com.thoughtworks.todoList.repository.TodoRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    TodoRepository todoRepository;

    @AfterEach
    void tearDown() {
        todoRepository.deleteAll();
    }

    @Test
    void should_return_todo_when_post_given_new_todo() throws Exception {
        //given
        String newTodo="{\n" +
                "    \"content\": \"666\"\n" +
                "}";
        //when
        mockMvc.perform(post("/todos").contentType(MediaType.APPLICATION_JSON).content(newTodo))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.content").value("666"));
        //then
    }

    @Test
    void should_return_todo_list_when_get_all_given_none() throws Exception {
        //given
        List<Todo> todoList = Collections.singletonList(new Todo("2222"));
        todoRepository.saveAll(todoList);
        //when
        mockMvc.perform(get("/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$[0].content").value("2222"));
        //then
    }

    @Test
    void should_return_void_when_deleye_given_id() throws Exception {
        //given

        Todo save = todoRepository.save(new Todo("2222"));
        //when
        mockMvc.perform(delete("/todos/"+save.getId()))
                .andExpect(status().isOk());
        //then
    }

    @Test
    void should_return_new_todo_when_update_given_id_new_todo() throws Exception {
        //given
        Todo save = todoRepository.save(new Todo(1,"2222",false));

        String newTodo="{   \n" +
                "    \"id\":"+save.getId()+",\n" +
                "    \"content\": \"3333\",\n" +
                "    \"status\":true\n" +
                "}";
        //when
        mockMvc.perform(put("/todos/"+save.getId()).contentType(MediaType.APPLICATION_JSON).content(newTodo))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(true));
        //then
    }


}
