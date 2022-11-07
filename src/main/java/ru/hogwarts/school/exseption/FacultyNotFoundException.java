package ru.hogwarts.school.exseption;

public class FacultyNotFoundException extends RuntimeException{
    private final long id;
    public FacultyNotFoundException(Long id) {
        this.id = id;
    }
    public long getid() {
        return id;
    }
}
