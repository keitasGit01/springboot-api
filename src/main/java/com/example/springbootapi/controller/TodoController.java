package com.example.springbootapi.controller;


import com.example.springbootapi.Entity.Todo;
import com.example.springbootapi.exception.TodoException;
import com.example.springbootapi.repository.TodoRepository;
import com.example.springbootapi.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.List;

@RestController
public class TodoController {

    @Autowired
    public TodoService service;


    @Autowired
    public TodoRepository repository;


    @GetMapping("/todos")
    public ResponseEntity<?> getAllTodo(){
        List<Todo> getAll = repository.findAll();
        if (getAll.size() > 0){
            return new ResponseEntity<>(getAll, HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Todo not available", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/todos")
    public ResponseEntity<?> saveTodo(@RequestBody Todo todo) throws TodoException {
        try{
            service.createTodo(todo);
            return new ResponseEntity<>(todo, HttpStatus.OK);
        }catch (ConstraintViolationException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }catch (TodoException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
}
