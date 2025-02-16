package org.example.crudApp.controller;

import jakarta.validation.Valid;
import org.example.crudApp.dao.PersonDAO;
import org.example.crudApp.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    // Model - это интерфейс Spring MVC, который позволяет передавать данные
// от контроллера к представлению. В представление можно передавать
// объекты, списки, строки и другие данные, которые будут отображены на веб-странице.
    @GetMapping
    public String index(Model model) throws SQLException {
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.show(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    //BindingResult bindingResult - это интерфейс в Spring Framework,
    // который содержит результаты валидации объекта.
    // Он используется для получения информации об ошибках, возникших при попытке
    // связать данные из запроса (например, из HTML-формы) с полями объекта.
    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "people/new";

        personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personDAO.show(id));
        return "people/edit";
    }

    // @PatchMapping: аннотация, указывающая, что этот метод будет обрабатывать
    // HTTP-запросы, использующие метод PATCH
    // @ModelAttribute("person"): аннотация указывает,
    // что объект Person будет создан из данных, отправленных в запросе.
    // @PathVariable("id"): Эта аннотация извлекает значение из URI-шаблона (/{id})
    // и связывает его с параметром id метода.
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "people/edit";

        personDAO.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/people";
    }
}

