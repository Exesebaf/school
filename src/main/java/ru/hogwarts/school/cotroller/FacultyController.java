package ru.hogwarts.school.cotroller;

import org.springframework.web.bind.annotation.*;

import ru.hogwarts.school.record.FacultyRecord;
import ru.hogwarts.school.service.FacultyService;


import javax.validation.Valid;
import java.util.Collection;


@RestController
@RequestMapping("faculties")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public FacultyRecord createFaculty(@RequestBody @Valid FacultyRecord facultyRecord) {
        return facultyService.createFaculty(facultyRecord);
    }

    @GetMapping("{id}")
    public FacultyRecord getFacultyId(@PathVariable Long id) {
        return facultyService.findFaculty(id);
    }


    @PutMapping("{id}")
    public FacultyRecord editFaculty(@PathVariable long id,
                                     @RequestBody @Valid FacultyRecord facultyRecord) {
        return facultyService.editFaculty(id, facultyRecord);
    }

    @DeleteMapping("{id}")
    public FacultyRecord deleteFaculty(@PathVariable long id) {
        return facultyService.deleteFaculty(id);
    }

    @GetMapping
    public Collection<FacultyRecord> findColorFaculty(@RequestParam String color) {
        return facultyService.findColorFaculty(color);
    }
}



