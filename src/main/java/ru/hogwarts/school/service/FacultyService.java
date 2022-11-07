package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exseption.FacultyNotFoundException;
import ru.hogwarts.school.mobel.Faculty;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private final Map<Long, Faculty> facultyMap = new HashMap<>();

    private Long idFaculty = 0L;


    public Faculty createFaculty(Faculty faculty) {
        faculty.setId(idFaculty++);
        facultyMap.put(faculty.getId(), faculty);
        return faculty;
    }

    public Faculty findFaculty(Long id) {
        if (!facultyMap.containsKey(id)) {
            throw new FacultyNotFoundException(id);
        }
        return facultyMap.get(id);
    }

    public Faculty editFaculty(Long id,
                               Faculty faculty) {
        if (!facultyMap.containsKey(id)) {
            throw new FacultyNotFoundException(id);
        }
        Faculty oldFasulty = facultyMap.get(id);
        oldFasulty.setName(faculty.getName());
        oldFasulty.setColor(faculty.getColor());
        facultyMap.replace(id, oldFasulty);
        return oldFasulty;
    }

    public Faculty deleteFaculty(Long id) {
        if (!facultyMap.containsKey(id)) {
            throw new FacultyNotFoundException(id);
        }
        return facultyMap.remove(id);
    }

    public Collection<Faculty> findColorFaculty(String color) {
        return facultyMap.values().stream()
                .filter(faculty -> faculty.getColor().equals(color))
                .collect(Collectors.toList());
    }

}
