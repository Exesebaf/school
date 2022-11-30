package ru.hogwarts.school.cotroller;


import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import ru.hogwarts.school.record.FacultyRecord;
import ru.hogwarts.school.record.StudentRecord;
import ru.hogwarts.school.service.StudentService;


import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/students")
@Validated
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public StudentRecord createStudent(@RequestBody @Valid StudentRecord studentRecord) {
        return studentService.createStudent(studentRecord);
    }

    @GetMapping("/{id}")
    public StudentRecord getStudentId(@PathVariable Long id) {
        return studentService.findStudent(id);
    }

    @PutMapping("/{id}")
    public StudentRecord editStudent(@PathVariable long id,
                                     @RequestBody @Valid StudentRecord studentRecord) {
        return studentService.editStudent(id, studentRecord);
    }

    @DeleteMapping("/{id}")
    public StudentRecord deleteStudent(@PathVariable Long id) {
        return studentService.deleteStudent(id);
    }


    @GetMapping(params = {"min", "max"})
    public Collection<StudentRecord> findByAgeBetween(@RequestParam int min,
                                                      @RequestParam int max) {
        return studentService.findAllByAgeBetween(min, max);
    }

    @GetMapping("/{id}/faculty")
    public FacultyRecord findFacultyByStudent(@PathVariable Long id) {
        return studentService.findFacultyByStudent(id);
    }

    @GetMapping("/totalCount")
    public int totalCountStudents() {
        return studentService.totalCountStudents();
    }

    @GetMapping("/averageAge")
    public double averageAgeOfStudents() {
        return studentService.averageAgeOfStudents();
    }

    @GetMapping("/lastStudents")
    public List<StudentRecord> lastStudents(@RequestParam @Min(1) @Max(10) int count){
        return studentService.lastStudents(count);
    }
}
