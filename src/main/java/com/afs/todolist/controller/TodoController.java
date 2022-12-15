package com.afs.todolist.controller;

import com.afs.todolist.controller.dto.TodoCreateRequest;
import com.afs.todolist.controller.mapper.TodoMapper;
import com.afs.todolist.entity.Todo;
import com.afs.todolist.exception.InvalidIdException;
import com.afs.todolist.service.TodoService;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RestController
@RequestMapping("/todos")
@CrossOrigin
public class TodoController {

    private final TodoService todoService;
    private final TodoMapper todoMapper;

    public TodoController(TodoService todoService, TodoMapper todoMapper) {
        this.todoService = todoService;
        this.todoMapper = todoMapper;
    }

    @GetMapping
    List<Todo> getAll() {
        return todoService.findAll();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Todo add(@RequestBody TodoCreateRequest todoRequest) {
        Todo todo = todoMapper.toEntity(todoRequest);
        return todoService.create(todo);
    }
    @PutMapping("/{id}")
    public Todo update(@PathVariable String id, @RequestBody TodoCreateRequest todoRequest) {
        Todo todo = todoMapper.toEntity(todoRequest);
        return todoService.update(id, todo);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        todoService.delete(id);
    }
}
