package com.example.springbootapi.Entity;


import lombok.*;

import java.util.Date;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Todo {

    private String id;
    private String todo;
    private String description;
    private Boolean completed;
    private Date createdAt;
    private Date updatedAt;

}
