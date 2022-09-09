package com.example.springbootapi.repository;

import com.example.springbootapi.Entity.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface TodoRepository extends MongoRepository<Todo, String> {



    @Query(value = "{'todo' : ?0}")
    Optional<Todo> findByTodo(String todo);
}
