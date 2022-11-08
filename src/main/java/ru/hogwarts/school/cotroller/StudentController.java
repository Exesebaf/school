package ru.hogwarts.school.cotroller;


import org.springframework.web.bind.annotation.*;

import ru.hogwarts.school.record.StudentRecord;
import ru.hogwarts.school.service.StudentService;


import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public StudentRecord createStudent(@RequestBody @Valid StudentRecord studentRecord) {
        return studentService.createStudent(studentRecord);
    }

    @GetMapping("{id}")
    public StudentRecord getStudentId(@PathVariable Long id) {
        return studentService.findStudent(id);
    }

    @PutMapping("{id}")
    public StudentRecord editStudent(@PathVariable long id,
                                     @RequestBody @Valid StudentRecord studentRecord) {
        return studentService.editStudent(id, studentRecord);
    }

    @DeleteMapping("{id}")
    public StudentRecord deleteStudent(@PathVariable Long id) {
        return studentService.deleteStudent(id);
    }

    @GetMapping
    public Collection<StudentRecord> findAgeStudent(@RequestParam int age) {
        return studentService.findAgeStudent(age);
    }

}
