package com.afs.todolist.service;

import com.afs.todolist.controller.dto.TodoCreateRequest;
import com.afs.todolist.entity.Todo;
import com.afs.todolist.exception.InvalidIdException;
import com.afs.todolist.exception.TodoNotFoundException;
import com.afs.todolist.repository.TodoRepository;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    private void validateObjectId(String id){
        if(!ObjectId.isValid(id)){
            throw new InvalidIdException(id);
        }
    }

    public Todo create(Todo todo) {
        todo.setDone(false);
        return todoRepository.save(todo);
    }

    public Todo update(String id, Todo todo){
        validateObjectId(id);

        Todo existingTodo = todoRepository.findById(id)
            .orElseThrow(() -> new TodoNotFoundException(id));

        if (todo.getText() != null) {
            existingTodo.setText(todo.getText());
        }
        if (todo.getDone() != null) {
            existingTodo.setDone(todo.getDone());
        }
        return todoRepository.save(existingTodo);
    }

    public void delete(String id) {
        validateObjectId(id);
        todoRepository.deleteById(id);
    }
}
