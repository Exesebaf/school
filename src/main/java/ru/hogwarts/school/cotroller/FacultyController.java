package ru.hogwarts.school.cotroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.mobel.Faculty;

import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;


@RestController
@RequestMapping("faculties")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public ResponseEntity<Faculty> createFaculty(@RequestBody Faculty faculty) {
        Faculty created = facultyService.createFaculty(faculty);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("{id}")
    public Faculty getFacultyId(@PathVariable Long id) {
        return facultyService.findFaculty(id);
    }


    @PutMapping("{id}")
    public Faculty editFaculty(@PathVariable long id,
                               @RequestBody Faculty faculty) {
        return facultyService.editFaculty(id, faculty);
    }

    @DeleteMapping("{id}")
    public Faculty deleteFaculty(@PathVariable long id) {
        return facultyService.deleteFaculty(id);
    }

    @GetMapping
    public Collection<Faculty> findColorFaculty(@RequestParam String color) {
        return facultyService.findColorFaculty(color);
    }
}



