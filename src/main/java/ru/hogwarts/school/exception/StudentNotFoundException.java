package ru.hogwarts.school.exception;

public class StudentNotFoundException extends RuntimeException {
    private final long id;

    public StudentNotFoundException(Long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
