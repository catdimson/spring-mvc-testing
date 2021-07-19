package ru.kotik.mvc.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Person {
    private int id;

    @NotEmpty(message = "Имя не может быть пустым")
    @Size(min = 2, max = 14, message = "Имя долнжо содержать от 2 до 14 символов")
    private String name;

    @Min(value = 0, message = "Возраст должен быть больше 0")
    private int age;

    @NotEmpty(message = "Email не должен быть пустым")
    @Email(message = "Email должен быть валидным")
    private String email;

    public Person() {}

    public Person(int id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
