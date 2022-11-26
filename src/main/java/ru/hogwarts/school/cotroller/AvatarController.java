package ru.hogwarts.school.cotroller;



import org.springframework.data.util.Pair;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.record.AvatarRecord;
import ru.hogwarts.school.service.AvatarService;

import javax.validation.constraints.Min;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/avatars")
@Validated
public class AvatarController {

    private final AvatarService avatarService;

    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @PostMapping(value = "/{studentId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public AvatarRecord create(@RequestParam long studentId,
                                     @RequestParam MultipartFile avatar) throws IOException {
        return avatarService.create(avatar, studentId);
    }
    @GetMapping("/{id}/from-fs")
    public ResponseEntity<byte[]> readFromFs(@PathVariable long id) throws IOException {
        Pair<byte[],String> pair = avatarService.readFromFs(id);
        return ResponseEntity.ok().contentLength(pair.getFirst().length)
                .contentType(MediaType.parseMediaType(pair.getSecond()))
                .body(pair.getFirst());
    }
    @GetMapping("/{id}/from-db")
    public ResponseEntity<byte[]> readFromDb(@PathVariable long id) throws IOException {
        Pair<byte[],String> pair = avatarService.readFromDb(id);
        return ResponseEntity.ok().contentLength(pair.getFirst().length)
                .contentType(MediaType.parseMediaType(pair.getSecond()))
                .body(pair.getFirst());
    }
    @GetMapping
    public List<AvatarRecord> findByPagination(@RequestParam @Min(0) int page ,
                                               @RequestParam @Min(0) int size) {
        return avatarService.findByPagination(page, size);
    }




}
