package ru.hogwarts.school.service;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.component.RecordMapper;
import ru.hogwarts.school.entity.Avatar;


import ru.hogwarts.school.entity.Student;
import ru.hogwarts.school.exception.AvatarNotFoundException;
import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.record.AvatarRecord;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.repository.StudentRepository;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class AvatarService {
    private static final Logger LOG = LoggerFactory.getLogger(AvatarService.class);
    private final AvatarRepository avatarRepository;
    private final StudentRepository studentRepository;

    private final RecordMapper recordMapper;

    @Value("${application.avatar.store}")
    private String avatarDir;


    public AvatarService(AvatarRepository avatarRepository,
                         StudentRepository studentRepository,
                         RecordMapper recordMapper) {
        this.avatarRepository = avatarRepository;
        this.studentRepository = studentRepository;
        this.recordMapper = recordMapper;
    }

    public AvatarRecord create(MultipartFile multipartFile, long studentId) throws IOException {
        LOG.debug("Method create Avatar was invoked");
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException(studentId));
        LOG.error("Avatar with studentId {} not found",studentId);
        byte[] data = multipartFile.getBytes();
        String extension = Optional.ofNullable(multipartFile.getOriginalFilename())
                .map(fileName -> fileName.substring(multipartFile.getOriginalFilename().lastIndexOf('.')))
                .orElse("");
        Path path = Paths.get(avatarDir).resolve(studentId + extension);
        Files.write(path, data);
        Avatar avatar = new Avatar();
        avatar.setData(data);
        avatar.setFileSize(data.length);
        avatar.setMediaType(multipartFile.getContentType());
        avatar.setStudent(student);
        avatar.setFilePath(path.toString());
        return recordMapper.toRecord(avatarRepository.save(avatar));
    }


    public Pair<byte[], String> readFromFs(long id) throws IOException {
        LOG.debug("Method readFromFs Avatar was invoked");
        Avatar avatar = avatarRepository.findById(id).orElseThrow(() -> new AvatarNotFoundException(id));
        LOG.error("Avatar with id {} not found",id);
        return Pair.of(Files.readAllBytes(Paths.get(avatar.getFilePath())), avatar.getMediaType());
    }

    public Pair<byte[], String> readFromDb(long id) throws IOException {
        LOG.debug("Method readFromDb Avatar was invoked");
        Avatar avatar = avatarRepository.findById(id).orElseThrow(() -> new AvatarNotFoundException(id));
        LOG.error("Avatar with id {} not found",id);
        return Pair.of(avatar.getData(), avatar.getMediaType());
    }


    public List<AvatarRecord> findByPagination(int page, int size) {
        LOG.debug("Method findByPagination Avatar was invoked");
        return  avatarRepository.findAll(PageRequest.of(page, size)).get()
                .map(recordMapper::toRecord)
                .collect(Collectors.toList());

    }
}
