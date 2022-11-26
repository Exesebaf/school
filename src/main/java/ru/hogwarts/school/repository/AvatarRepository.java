package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.entity.Avatar;
import ru.hogwarts.school.record.AvatarRecord;

import java.util.List;

@Repository
public interface AvatarRepository extends JpaRepository<Avatar,Long> {

}
