package ru.hogwarts.school.record;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class StudentRecord {
    private Long id;

    @NotBlank(message = "Имя студента должно быть заполнено!")
    private String name;
    @Min(value = 16, message = "Минимальный возраст студента 16 лет!")
    @Max(value = 26, message = "Максимальный возраст студента 26 лет!")
    private int age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
}
