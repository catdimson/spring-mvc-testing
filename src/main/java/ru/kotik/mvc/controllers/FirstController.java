package ru.kotik.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping(path = "/root")
public class FirstController {

    @GetMapping(path = "/hello")
    public String helloPage(HttpServletResponse response, HttpServletRequest request) throws UnsupportedEncodingException {
        // путь к таймлиф шаблону
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        return "first/hello";
    }

    @GetMapping(path = "goodbye")
    public String gooByePage(HttpServletResponse response, HttpServletRequest request) throws UnsupportedEncodingException {
        // путь к таймлиф шаблону
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        return "first/goodbye";
    }

}
