package ru.kotik.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping(path = "/root", produces = "text/plain;charset=UTF-8")
public class FirstController {

    @GetMapping(path = "/hello")
    public String helloPage(HttpServletResponse response, HttpServletRequest request) throws UnsupportedEncodingException {
        // путь к таймлиф шаблону
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        // params
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        System.out.println("Hello, " + name + " " + surname);
        return "first/hello";
    }

    @GetMapping(path = "/goodbye")
    public String gooByePage(@RequestParam("name") String name,
                             @RequestParam("surname") String surname,
                             HttpServletResponse response,
                             HttpServletRequest request) throws UnsupportedEncodingException {
        // путь к таймлиф шаблону
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        // params
        System.out.println("Hello, " + name + " " + surname);
        return "first/goodbye";
    }

    @GetMapping(path = "/goodbye")
    public String gooByePage(HttpServletResponse response,
                             HttpServletRequest request) throws UnsupportedEncodingException {
        // путь к таймлиф шаблону
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        // params
        return "first/goodbye";
    }



}
