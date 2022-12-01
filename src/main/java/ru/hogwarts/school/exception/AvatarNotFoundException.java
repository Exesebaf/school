package ru.hogwarts.school.exception;

public class AvatarNotFoundException extends RuntimeException {
    private final long id;

    public AvatarNotFoundException(Long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

}
