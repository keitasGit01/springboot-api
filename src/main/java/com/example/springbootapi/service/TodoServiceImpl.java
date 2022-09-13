package com.example.springbootapi.service;

import com.example.springbootapi.Entity.Todo;
import com.example.springbootapi.exception.TodoException;
import com.example.springbootapi.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class TodoServiceImpl implements TodoService{
    
    
    @Autowired
    private TodoRepository repository;

    @Override
    public void createTodo(Todo todo) throws TodoException {
        Optional<Todo>todos = repository.findByTodo(todo.getTodo());
        if (todos.isPresent()){
            throw new TodoException(TodoException.TodoAlreadyExist());

        }else {
            todo.setCreatedAt(new Date(System.currentTimeMillis()));
            todo.setUpdatedAt(new Date(System.currentTimeMillis()));
            repository.save(todo);
        }
    }

    @Override
    public List<Todo> getAllTodo() {
        List<Todo> getAllTodo = repository.findAll();
        if (getAllTodo.size() > 0){
            return getAllTodo;
        }else {
            return new ArrayList<>();
        }

    }

    @Override
    public Todo getTodoId(String id) throws TodoException {
        Optional<Todo> getSingleTodo = repository.findById(id);
        if (!getSingleTodo.isPresent()){
            throw new TodoException(TodoException.NotFoundException(id));

        }else {
            return getSingleTodo.get();

        }
    }


}
