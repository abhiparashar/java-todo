package com.example.todoapispring;

import org.springframework.stereotype.Service;

@Service("anotherTodoService")
public class AnotherTodoService implements TodoService {
    @Override
    public String doSomething(){
        return "Another Todo Something";
    }
}
