package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.component.RecordMapper;
import ru.hogwarts.school.exception.FacultyNotFoundException;
import ru.hogwarts.school.entity.Faculty;
import ru.hogwarts.school.record.FacultyRecord;
import ru.hogwarts.school.record.StudentRecord;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private static final Logger LOG = LoggerFactory.getLogger(FacultyService.class);

    private final FacultyRepository facultyRepository;

    private final RecordMapper recordMapper;

    public FacultyService(FacultyRepository facultyRepository, RecordMapper recordMapper) {
        this.facultyRepository = facultyRepository;
        this.recordMapper = recordMapper;
    }

    public FacultyRecord createFaculty(FacultyRecord facultyRecord) {
        LOG.debug("Method createFaculty  was invoked");
        return recordMapper.toRecord(facultyRepository.save(recordMapper.toEntity(facultyRecord)));
    }

    public FacultyRecord findFaculty(Long id) {
        LOG.debug("Method findFaculty  was invoked");
        return recordMapper.toRecord(facultyRepository.findById(id).orElseThrow(() -> {
            LOG.error("Faculty with id {} not found", id);
            return new FacultyNotFoundException(id);
        }));
    }

    public FacultyRecord editFaculty(Long id,
                                     FacultyRecord facultyRecord) {
        LOG.debug("Method editFaculty  was invoked");
        Faculty oldFaculty = (facultyRepository.findById(id).orElseThrow(() ->{
            LOG.error("Faculty with id {} not found", id);
            return new FacultyNotFoundException(id);
        }));
        oldFaculty.setName(facultyRecord.getName());
        oldFaculty.setColor(facultyRecord.getColor());
        return recordMapper.toRecord(facultyRepository.save(oldFaculty));
    }

    public FacultyRecord deleteFaculty(Long id) {
        LOG.debug("Method deleteFaculty was invoked");
        Faculty faculty = (facultyRepository.findById(id).orElseThrow(() ->{
            LOG.error("Faculty with id {} not found", id);
            return new FacultyNotFoundException(id);
        }));
        facultyRepository.delete(faculty);
        return recordMapper.toRecord(faculty);
    }

    public Collection<FacultyRecord> findColorFaculty(String color) {
        LOG.debug("Method findColorFaculty was invoked");
        return facultyRepository.findAllByColor(color).stream()
                .map(recordMapper::toRecord)
                .collect(Collectors.toList());
    }

    public Collection<FacultyRecord> findByNameOrColor(String colorOrName) {
        LOG.debug("Method findByNameOrColor was invoked");
        return facultyRepository.findAllByColorIgnoreCaseOrNameIgnoreCase(colorOrName, colorOrName).stream()
                .map(recordMapper::toRecord)
                .collect(Collectors.toList());

    }

    public Collection<StudentRecord> findStudentsByFaculty(long id) {
        LOG.debug("Method findStudentsByFaculty was invoked");
        return facultyRepository.findById(id)
                .map(Faculty::getStudents)
                .map(students ->
                        students.stream()
                        .map(recordMapper::toRecord)
                                .collect(Collectors.toList()))
                .orElseThrow(() ->{LOG.error("Faculty with id {} not found", id);
                return new FacultyNotFoundException(id);
                });












    }


}
