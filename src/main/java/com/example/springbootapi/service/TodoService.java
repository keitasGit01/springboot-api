package com.example.springbootapi.service;

import com.example.springbootapi.Entity.Todo;
import com.example.springbootapi.exception.TodoException;

import java.util.List;

public interface TodoService {

      void createTodo(Todo todo) throws TodoException;

     List<Todo> getAllTodo();

     Todo getTodoId(String id) throws TodoException;

}
