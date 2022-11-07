package ru.hogwarts.school.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.hogwarts.school.exseption.FacultyNotFoundException;
import ru.hogwarts.school.exseption.StudentNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<String> handlerStudentNonFoundException(StudentNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Студент с id =" + e.getid() + " не найдет");
    }

    @ExceptionHandler(FacultyNotFoundException.class)
    public ResponseEntity<String> handlerFacultyNotFoundException(FacultyNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Факультет с id =" + e.getid() + " не найдет");
    }
}
