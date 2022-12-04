package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import ru.hogwarts.school.component.RecordMapper;
import ru.hogwarts.school.entity.Faculty;

import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.entity.Student;
import ru.hogwarts.school.record.FacultyRecord;
import ru.hogwarts.school.record.StudentRecord;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class StudentService {
    private static final Logger LOG = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;

    private final RecordMapper recordMapper;


    public StudentService(StudentRepository studentRepository,
                          FacultyRepository facultyRepository,
                          RecordMapper recordMapper) {
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
        this.recordMapper = recordMapper;
    }

    public StudentRecord createStudent(StudentRecord studentRecord) {
        LOG.debug("Method createStudent  was invoked");
        Student student = recordMapper.toEntity(studentRecord);
        Faculty faculty = Optional.ofNullable(studentRecord.getFaculty())
                .map(FacultyRecord::getId)
                .flatMap(facultyRepository::findById)
                .orElse(null);
        student.setFaculty(faculty);
        return recordMapper.toRecord(studentRepository.save(student));
    }

    public StudentRecord findStudent(Long id) {
        LOG.debug("Method findStudent  was invoked");
        return recordMapper.toRecord(studentRepository.findById(id).orElseThrow(() -> {
            LOG.debug("Student with id {} not found", id);
            return new StudentNotFoundException(id);
        }));
    }

    public StudentRecord editStudent(long id,
                                     StudentRecord studentRecord) {
        LOG.debug("Method editStudent  was invoked");
        Student oldStudent = studentRepository.findById(id).orElseThrow(() -> {
            LOG.debug("Student with id {} not found", id);
            return new StudentNotFoundException(id);
        });
        oldStudent.setName(studentRecord.getName());
        oldStudent.setAge(studentRecord.getAge());
        oldStudent.setFaculty(
                Optional.ofNullable(studentRecord.getFaculty())
                        .map(FacultyRecord::getId)
                        .flatMap(facultyRepository::findById)
                        .orElse(null)
        );
        return recordMapper.toRecord(studentRepository.save(oldStudent));
    }

    public StudentRecord deleteStudent(Long id) {
        LOG.debug("Method deleteStudent  was invoked");
        Student student = studentRepository.findById(id).orElseThrow(() -> {
            LOG.debug("Student with id {} not found", id);
            return new StudentNotFoundException(id);
        });
        studentRepository.delete(student);
        return recordMapper.toRecord(student);
    }


    public Collection<StudentRecord> findAllByAgeBetween(int min, int max) {
        LOG.debug("Method deleteStudent  was invoked");
        return studentRepository.findAllByAgeBetween(min, max).stream()
                .map(recordMapper::toRecord)
                .collect(Collectors.toList());
    }

    public FacultyRecord findFacultyByStudent(Long id) {
        LOG.debug("Method findFacultyByStudent  was invoked");
        return findStudent(id).getFaculty();
    }


    public int totalCountStudents() {
        LOG.debug("Method totalCountStudents  was invoked");
        return studentRepository.totalCountOfStudents();
    }

    public double averageAgeOfStudents() {
        LOG.debug("Method averageAgeOfStudents  was invoked");
        return studentRepository.averageAgeOfStudents();
    }

    public List<StudentRecord> lastStudents(int count) {
        LOG.debug("Method lastStudents  was invoked");
        return studentRepository.lastStudents(count).stream()
                .map(recordMapper::toRecord)
                .collect(Collectors.toList());
    }

    public Stream<String> findStudentNameWitchA() {
        LOG.debug("Method lastStudents  was invoked");
        return studentRepository.findAll().stream()
                .map(Student::getName)
                .map(String::toUpperCase)
                .filter(s -> s.startsWith("A"))
                .sorted();
    }

    public double findStudentAverageAge() {
        LOG.debug("Method lastStudents  was invoked");
        return studentRepository.findAll().stream()
                .mapToDouble(Student::getAge)
                .average()
                .orElseThrow(null);
    }
}
