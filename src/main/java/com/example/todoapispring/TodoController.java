package com.example.todoapispring;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {
    private static List<Todo> todoList;
    private final TodoService todoService;
    public TodoController(TodoService todoService){
        this.todoService = todoService;
        todoList = new ArrayList<>();
        todoList.add(new Todo(1,true,"coding",1));
        todoList.add(new Todo(2,false,"assignment", 2));
    }

    @GetMapping
    public ResponseEntity<List<Todo>> getTodos(){
        System.out.println(this.todoService.doSomething());
        return ResponseEntity.status(HttpStatus.OK).body(todoList);
    }

    @PostMapping
    public ResponseEntity<Todo> createTodo(@RequestBody Todo newTodo){
        /*
        @ResponseStatus(HttpStatus.CREATED)
        */
        todoList.add(newTodo);
       return ResponseEntity.status(HttpStatus.CREATED).body(newTodo);
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<Todo> getSingleTodo(@PathVariable long todoId){
        for (Todo todo:todoList){
            if(todo.getId()==todoId){
                return ResponseEntity.ok(todo);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{todoId}")
    public ResponseEntity<?> updateTodo(
            @PathVariable int todoId,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Boolean completed
    ) {
        for(Todo todo : todoList) {
            if(todo.getId() == todoId) {
                if (title != null) {
                    todo.setTitle(title);
                }
                if (completed != null) {
                    todo.setCompleted(completed);
                }
                return ResponseEntity.ok(todo);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<?> deleteTodo(@PathVariable Long todoId){
        Todo removeTodo = null;
        for(Todo todo:todoList){
            if(todo.getId()==todoId){
              removeTodo = todo;
            }
        }
        if(removeTodo != null){
            todoList.remove(removeTodo);
            String deleteSuccessMessage = "Todo deleted successfully";
            return ResponseEntity.status(HttpStatus.OK).body(deleteSuccessMessage);
        }
        return ResponseEntity.notFound().build();
    }
}
