package com.thoughtworks.todoList.dto;

import java.util.Objects;

public class TodoRequest {
    private Integer id;
    private String content;
    private Boolean status;

    public TodoRequest() {
    }

    public TodoRequest(Integer id, String content, Boolean status) {
        this.id = id;
        this.content = content;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TodoRequest that = (TodoRequest) o;
        return id.equals(that.id) &&
                content.equals(that.content) &&
                status.equals(that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, status);
    }
}
