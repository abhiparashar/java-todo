package com.example.todoapispring;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("fakeTodoService")
public class FakeTodoService implements TodoService {
    public String doSomething(){
        return "Do Something from Fake todo class";
    }
}
