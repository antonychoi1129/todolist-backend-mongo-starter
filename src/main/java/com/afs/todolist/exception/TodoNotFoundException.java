package com.afs.todolist.exception;

import java.text.MessageFormat;

public class TodoNotFoundException extends RuntimeException{
    public TodoNotFoundException(String id) {
        super(MessageFormat.format("todo id {0} not found", id));
    }
}
