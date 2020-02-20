package se.su.it.latte.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import se.su.it.courseservice.courseinformation.client.api.model.Institution;
import se.su.it.courseservice.courseinformation.client.api.model.LadokKurstillfalle;
import se.su.it.courseservice.courseinformation.client.api.model.LadokStudent;
import se.su.it.latte.service.LadokService;
import se.su.it.latte.teexam.TeExamsApi;
import se.su.it.latte.teexam.models.*;

import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class LatteController {
    @Autowired
    LadokService ladokService;

    @Autowired
    TeExamsApi teExamsApi;


    @GetMapping("/join")
    public ResponseEntity join() {
        List<String> s = Arrays.asList("a", "b", "c");
        return ResponseEntity.ok(s.stream().map(str -> str + "...").collect(Collectors.joining(", ")));
    }

    @GetMapping("/r")
    public ResponseEntity readLadok() {
        Optional<List<LadokKurstillfalle>> ladok = ladokService.readLadok();
        if(ladok.isPresent()) {
            return ResponseEntity.ok(ladok.get());
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/i")
    public ResponseEntity getInstitution() {
        Optional<Institution> ladok = ladokService.getInstitution();
        if(ladok.isPresent()) {
            return ResponseEntity.ok(ladok.get());
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/l")
    public ResponseEntity listIds(){
        Optional<List<String>> codes = ladokService.getInstitutionCodes();
        if(codes.isPresent()) {
            return ResponseEntity.ok(codes.get());
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/s")
    public ResponseEntity getStudent(){
        log.debug("Looking up students");
        //String lid = "7b2c77b8-c7f4-11e9-ad45-0f23a57e4733"; // Anonymt
        String lid = "ba693ff7-296b-11e9-81b5-eaf0656641fc";
        //String lid =      "df5e7979-2ad2-11e9-81b5-eaf0656641fc"; // inte anonymt
        List<LadokStudent> codes = ladokService.findStudents(lid);
        if(codes.size() > 0) {
            return ResponseEntity.ok(codes);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/exams")
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> exams(){
        log.trace("exams");
        Optional<ExamsApiEntity> exams = teExamsApi.getExams();
        if(!exams.isPresent()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(exams);
    }

    @GetMapping("/exam/{id}")
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> exam(@PathVariable int id){
        log.trace("exam/{}", id );
        Optional<ExamApiEntity> exams = teExamsApi.getExam(id);
        if(!exams.isPresent()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(exams);
    }

    @GetMapping("/exam/{id}/registered-students")
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> examStudent(@PathVariable int id){
        log.trace("/exam/{}/registered-students", id );
        Optional<ExamStudentApiEntity> examsStudents = teExamsApi.getExamRegisteredStudents(id);
        if(examsStudents.isPresent()){
            return ResponseEntity.ok(examsStudents);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/transfere")
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> exam(@QueryParam("lid") String lid, @QueryParam("tid") String tid){
        if(lid == null || tid == null){
            return ResponseEntity.badRequest().build();
        }

        List<LadokStudent> ladokStudents = ladokService.findStudents(lid);


        List<Student> studentList = mapLadokStudentsToExamStudents(ladokStudents, lid);
        //teExamsApi.registerStudents(studentList, tid);
        studentList.forEach(student -> teExamsApi.registerStudents(student, tid));

        return ResponseEntity.ok("{tid: " + tid + ", lid: " + lid + " [ " + ladokStudents + "]}");
    }

    @GetMapping("/things")
    public void hello(){
        ladokService.findAktivitet();
    }

    private List<Student> mapLadokStudentsToExamStudents(List<LadokStudent> ladokStudents, String lid) {
        return ladokStudents.stream().map( ladokStudent -> mapLadokStudentToExamStudent(ladokStudent, lid)).collect(Collectors.toList());
    }

    private Student mapLadokStudentToExamStudent(LadokStudent ladokStudent, String lid) {
        Student student = new Student();
        student.setExamId(lid);
        student.setStudentId(ladokStudent.getSukatUid());
        student.setStudentName(ladokStudent.getFornamn() + " " + ladokStudent.getEfternamn());
        student.setStudentPersonalIdentificationNumber(ladokStudent.getPersonnummer());

        return student;
    }
}
