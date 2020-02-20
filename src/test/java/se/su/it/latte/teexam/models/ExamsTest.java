package se.su.it.latte.teexam.models;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExamsTest {

    @Test
    public void GetterSetterTest(){
        int id = 1;
        String name = "doggie";
        ExamStatus status = ExamStatus.AVAILABLE;

        List<String> photoUrls = Arrays.asList("http://localhost:8080/img/images.jpg", "http://localhost:8080/img/image.jpg");
        Exams exams = new Exams();

//        exams.setId(id);
//        exams.setName(name);
//        exams.setPhotoUrls(photoUrls);
        exams.setStatus(status);

//        assertEquals(exams.getId(), id);
//        assertEquals(exams.getName(), name);
//        assertEquals(exams.getPhotoUrls(), photoUrls);
        assertEquals(exams.getStatus(), status
        );
    }
}
