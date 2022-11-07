package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;


import ru.hogwarts.school.exseption.StudentNotFoundException;
import ru.hogwarts.school.mobel.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class StudentService {
    private final Map<Long, Student> studentMap = new HashMap<>();

    private Long idStudents = 0L;

    public Student createStudent(Student student) {
        student.setId(++idStudents);
        studentMap.put(idStudents, student);
        return student;
    }

    public Student findStudent(Long id) {
        if (!studentMap.containsKey(id)) {
            throw new StudentNotFoundException(id);
        }
        return studentMap.get(id);
    }

    public Student editStudent(long id,
                               Student student) {
        if (!studentMap.containsKey(id)) {
            throw new StudentNotFoundException(id);
        }
        Student oldStudent = studentMap.get(id);
        oldStudent.setAge(student.getAge());
        oldStudent.setName(student.getName());
        studentMap.replace(id, oldStudent);
        return oldStudent;
    }

    public Student deleteStudent(Long id) {
        if (!studentMap.containsKey(id)) {
            throw new StudentNotFoundException(id);
        }
        return studentMap.remove(id);
    }


    public Collection<Student> findAgeStudent(int age) {
        return studentMap.values().stream()
                .filter(student -> student.getAge() == age)
                .collect(Collectors.toList());
    }
}
