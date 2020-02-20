package se.su.it.latte.teexam;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import se.su.it.latte.teexam.models.ExamApiEntity;
import se.su.it.latte.teexam.models.ExamStudentApiEntity;
import se.su.it.latte.teexam.models.ExamsApiEntity;
import se.su.it.latte.teexam.models.Student;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TeExamsApi {
    @Value("${te_exam.api_url}")
    private String teExamUrl;

    @Value("${te_exam.api_token}")
    private String token;


    @Autowired
    private RestTemplate restTemplate;


    public Optional<ExamsApiEntity> getExams() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<ExamsApiEntity> exams = restTemplate.exchange(teExamUrl + "/exams", HttpMethod.GET, request, ExamsApiEntity.class);
        return Optional.of(exams.getBody());
    }

     public Optional<ExamApiEntity> getExam(int id){
         HttpHeaders headers = new HttpHeaders();
         headers.setBearerAuth(token);
         HttpEntity request = new HttpEntity(headers);
         ResponseEntity<ExamApiEntity> exams = restTemplate.exchange(teExamUrl + "/exams/" + id, HttpMethod.GET, request, ExamApiEntity.class);
        return Optional.of(exams.getBody());
     }

    public Optional<ExamStudentApiEntity> getExamRegisteredStudents(int id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<ExamStudentApiEntity> exams = restTemplate.exchange(teExamUrl + "/exams/" + id + "/registered-students", HttpMethod.GET, request, ExamStudentApiEntity.class);
        return Optional.of(exams.getBody());
    }

    public void registerStudents(Student payload, String id) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(token);
            HttpEntity request = new HttpEntity<Student>(payload, headers);
            restTemplate.exchange(teExamUrl + "/exams/" + id + "/registered-students", HttpMethod.POST, request, Student.class);
        }catch (Exception e){
            String studentId = Optional.ofNullable(payload.getStudentId()).orElse("could not get studentId");
            log.error("Could not register student with ID: {}, check stack trace for more information", studentId, e);
        }
    }

    public void registerStudents(List<Student> payload, String id) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(token);
            HttpEntity request = new HttpEntity<>(payload, headers);
            restTemplate.exchange(teExamUrl + "/exams/" + id + "/registered-students", HttpMethod.PUT, request, List.class);
        }catch (Exception e){
            String studentId = payload.stream().map(Student::getStudentId).collect(Collectors.joining(", "));
            log.error("Could not register student with ID: {}, check stack trace for more information", studentId, e);
        }
    }
}
