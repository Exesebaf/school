//package ru.hogwarts.school;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.boot.test.mock.mockito.SpyBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//
//import org.springframework.mock.web.MockHttpServletResponse;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import ru.hogwarts.school.component.RecordMapper;
//import ru.hogwarts.school.entity.Faculty;
//import ru.hogwarts.school.record.FacultyRecord;
//import ru.hogwarts.school.repository.FacultyRepository;
//import ru.hogwarts.school.service.FacultyService;
//
//import java.nio.charset.StandardCharsets;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//
//@WebMvcTest(controllers = FacultyControllerTest.class)
//@ExtendWith(MockitoExtension.class)
public class FacultyControllerTest {
//    @Autowired
//    MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @MockBean
//    private FacultyRepository facultyRepository;
//
//    @SpyBean
//    private FacultyService facultyService;
//
//    @SpyBean
//    private RecordMapper recordMapper;
//
//    @Test
//    public void crateTest() throws Exception {
//        Faculty faculty = new Faculty();
//        faculty.setId(1L);
//        faculty.setColor("желтый");
//        faculty.setName("Хаплаф");
//
//        when(facultyRepository.save(any())).thenReturn(faculty);
//
//        FacultyRecord facultyRecord = new FacultyRecord();
//        facultyRecord.setColor("Желтый");
//        facultyRecord.setName("Хаплаф");
//
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/faculties")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(facultyRecord)))
//                .andExpect(result -> {
//                    MockHttpServletResponse mockHttpServletResponse = result.getResponse();
//                    FacultyRecord facultyRecordResult = objectMapper.readValue(mockHttpServletResponse.getContentAsString(StandardCharsets.UTF_8), FacultyRecord.class);
//                    assertThat(mockHttpServletResponse.getStatus()).isEqualTo(HttpStatus.OK.value());
//                    assertThat(facultyRecordResult).isNotNull();
//                    assertThat(facultyRecordResult).usingRecursiveComparison().ignoringFields("id").isEqualTo(facultyRecord);
//                    assertThat(facultyRecordResult.getId()).isEqualTo(faculty.getId());
//                });
//
//    }
//    @Test
//    public void findFacultyTest() throws Exception {
//        Faculty faculty = new Faculty();
//        faculty.setId(1L);
//        faculty.setColor("желтый");
//        faculty.setName("Хаплаф");
//
//        when(facultyRepository.findById(1L)).thenReturn(Optional.of(faculty));
//        FacultyRecord facultyRecord = new FacultyRecord();
//        facultyRecord.setColor("желтый");
//        facultyRecord.setName("Хаплаф");
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/faculties/{id}",1L)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(facultyRecord)))
//                .andExpect(result -> {
//                            MockHttpServletResponse mockHttpServletResponse = result.getResponse();
//                    FacultyRecord facultyRecordResult = objectMapper.readValue(mockHttpServletResponse.getContentAsString(StandardCharsets.UTF_8), FacultyRecord.class);
//                    assertThat(mockHttpServletResponse.getStatus()).isEqualTo(HttpStatus.OK.value());
//                    assertThat(facultyRecordResult.getId()).isNotNull();
//                    assertThat(facultyRecordResult).usingRecursiveComparison().ignoringFields("id").isEqualTo(facultyRecord);
//                    assertThat(facultyRecordResult.getId()).isEqualTo(faculty.getId());
//                        });
//    }


}
