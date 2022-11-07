package ru.hogwarts.school.cotroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.hogwarts.school.mobel.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student created = studentService.createStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("{id}")
    public Student getStudentId(@PathVariable Long id) {
        return studentService.findStudent(id);
    }

    @PutMapping("{id}")
    public Student editStudent(@PathVariable long id,
            @RequestBody Student student) {
        return studentService.editStudent(id,student);
    }

    @DeleteMapping("{id}")
    public Student deleteStudent(@PathVariable Long id) {
        return studentService.deleteStudent(id);
    }

    @GetMapping
    public Collection<Student> findAgeStudent(@RequestParam int age) {
        return studentService.findAgeStudent(age);
    }

}
