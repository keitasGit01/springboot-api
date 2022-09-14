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

    @GetMapping("/todos/{id}")
    public ResponseEntity<?> getSingleTodo(@PathVariable String id) throws TodoException {
        try{
            return new ResponseEntity<>(service.getTodoId(id), HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/todos/{id}")
    public ResponseEntity<?> updatedTodo(@PathVariable String id, @RequestBody Todo todo){
        try {
            service.updatedTodo(id, todo);
            return new ResponseEntity<>("Updated to do with id " + id, HttpStatus.OK);
        }catch (ConstraintViolationException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }catch (TodoException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity<String> deleteById(@PathVariable String id){
        try {
            service.deleteTodo(id);
            return new ResponseEntity<>("Successfull delete with id "  + id, HttpStatus.OK);

        }catch (Exception e){

            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

        }
    }

}
