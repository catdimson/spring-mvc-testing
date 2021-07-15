package ru.kotik.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "/root")
@Controller
public class HelloWorld {

    @GetMapping(path = "/hello-world")
    public String sayHello() {
        return "hello_world";
    }

}
