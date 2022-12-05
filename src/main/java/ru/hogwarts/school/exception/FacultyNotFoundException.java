package ru.hogwarts.school.exception;

public class FacultyNotFoundException extends RuntimeException {
    private final long id;

    public FacultyNotFoundException(Long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
