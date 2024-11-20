package com.example.todoapispring;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class FakeTodoService implements TodoService {
    public String doSomething(){
        return "doSomething";
    }
}
