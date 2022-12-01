package ru.hogwarts.school.repository;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.entity.Avatar;
import ru.hogwarts.school.record.AvatarRecord;

import java.util.List;


@Repository
public interface AvatarRepository extends PagingAndSortingRepository<Avatar,Long> {

}
