package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.component.RecordMapper;
import ru.hogwarts.school.exception.FacultyNotFoundException;
import ru.hogwarts.school.entity.Faculty;
import ru.hogwarts.school.record.FacultyRecord;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    private final RecordMapper recordMapper;

    public FacultyService(FacultyRepository facultyRepository, RecordMapper recordMapper) {
        this.facultyRepository = facultyRepository;
        this.recordMapper = recordMapper;
    }

    public FacultyRecord createFaculty(FacultyRecord facultyRecord) {
        return recordMapper.toRecord(facultyRepository.save(recordMapper.toEntity(facultyRecord)));
    }

    public FacultyRecord findFaculty(Long id) {
        return recordMapper.toRecord(facultyRepository.findById(id).orElseThrow(() -> new FacultyNotFoundException(id)));
    }

    public FacultyRecord editFaculty(Long id,
                                     FacultyRecord facultyRecord) {
        Faculty oldFaculty = (facultyRepository.findById(id).orElseThrow(() -> new FacultyNotFoundException(id)));
        oldFaculty.setName(facultyRecord.getName());
        oldFaculty.setColor(facultyRecord.getColor());
        return recordMapper.toRecord(facultyRepository.save(oldFaculty));
    }

    public FacultyRecord deleteFaculty(Long id) {
        Faculty faculty = (facultyRepository.findById(id).orElseThrow(() -> new FacultyNotFoundException(id)));
        facultyRepository.delete(faculty);
        return recordMapper.toRecord(faculty);
    }

    public Collection<FacultyRecord> findColorFaculty(String color) {
        return facultyRepository.findAllByColor(color).stream()
                .map(recordMapper::toRecord)
                .collect(Collectors.toList());
    }

}
