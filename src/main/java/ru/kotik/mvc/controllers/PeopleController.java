package ru.kotik.mvc.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kotik.mvc.dao.PersonDAO;
import ru.kotik.mvc.models.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(HttpServletResponse response,
                        HttpServletRequest request,
                        Model model) throws UnsupportedEncodingException {
        response.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
        request.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.show(id));
        return "people/show";
    }

    // используем @ModelAttribute. Если параметры не будут переданы (это м.б. и GET параметры, передаваемые в адресной
    // строке) то значения будут пустыми, что нам и нужно.
    @GetMapping("/new")
    public String newPerson(/*Model model*/ @ModelAttribute("person") Person person, HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
        /*model.addAttribute("person", new Person());*/
        return "people/new";
    }

    // автоматически разносятся параметры по полям формы с помощью @ModelAttribute
    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            /*--ошибки автоматически будут внедрены и отображены на странице--*/
            return "people/new";
        }
        personDAO.save(person);
        return "redirect:/people";
    }

    // работаем вручную с Model model, @ModelAttribute не используем. Ведь нам нужно заполнить форму объекта
    // соответстующими значениями
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable int id) {
        model.addAttribute("person", personDAO.show(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "people/new";
        }
        personDAO.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        personDAO.delete(id);
        return "redirect:/people";
    }

    /*@PostMapping("/something")
    public String something(@RequestBody String body, Model model) {
        model.addAttribute("body", body);
        return "people/SOMETHING";
    }*/
}
