package se.su.it.latte.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import se.su.it.courseservice.courseinformation.client.api.CourseInformationServiceClient;
import se.su.it.courseservice.courseinformation.client.api.CurrentUser;
import se.su.it.courseservice.courseinformation.client.api.model.Institution;
import se.su.it.courseservice.courseinformation.client.api.model.LadokKurstillfalle;
import se.su.it.courseservice.courseinformation.client.api.model.LadokStudent;
import se.su.it.courseservice.courseinformation.client.api.model.LadokStudentStudiedeltagande;
import se.su.it.courseservice.courseinformation.client.api.properties.CourseInformationServiceClientProperties;
import se.su.it.courseservice.courseinformation.client.impl.CourseInformationServiceClientImpl;
import se.su.it.courseservice.courseinformation.client.impl.CourseInformationServiceClientPropertiesImpl;
import se.su.it.latte.configurations.CurrentUserImpl;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class LadokService {

    @Autowired
    private CourseInformationServiceClient courseInformationServiceClient;



    @Bean
    public CourseInformationServiceClient courseInformationServiceClient() {
        return new CourseInformationServiceClientImpl();
    }

    @Bean
    public CourseInformationServiceClientProperties courseInformationServiceClientProperties() {
        return new CourseInformationServiceClientPropertiesImpl();
    }

    @Bean
    public CurrentUser currentUser() {
        return new CurrentUserImpl();
    }

    public Optional<List<String>> getInstitutionCodes(){
        List<Institution> institutions = courseInformationServiceClient.findAllInstitutioner();
        List<String> ids = institutions
                .stream()
                .map(Institution::getKod)
                .collect(Collectors.toList());
        return Optional.of(ids);
    }

    public Optional<Institution> getInstitution(){
        String institutionId = "104";
        Institution institution = courseInformationServiceClient.findInstitutionByKod(institutionId);
        return Optional.of(institution);
    }

    public  Optional<List<LadokKurstillfalle>> readLadok() {
        long fifteenth = 1576400642000L;
        long fifth  = 1575538019000L;

        String institutionId = "104";

        Date now = new Date(fifth);
        Date later = new Date(fifteenth);

        List<LadokKurstillfalle> ladokKurstillfalle = courseInformationServiceClient.findByEndDateInPeriod(institutionId, now, later);

        Optional<List<LadokKurstillfalle>> ladokKurstillfalle1 = Optional.of(ladokKurstillfalle);

        return ladokKurstillfalle1;
    }

    public List<LadokStudent> getFromLadok(String ladokUid){
        LadokKurstillfalle lkf = courseInformationServiceClient.findKurstillfalleByUid("ba693ff7-296b-11e9-81b5-eaf0656641fc");
        LadokStudentStudiedeltagande lssd =  courseInformationServiceClient.getStudiedeltagandeByLadokUid("7b2c77b8-c7f4-11e9-ad45-0f23a57e4733");
        log.debug("Lkf: {}", lkf);

        List<LadokStudent> ladokStudents =  courseInformationServiceClient.findStudents(ladokUid);
        ladokStudents.forEach(ladokStudent -> log.debug(String.valueOf(ladokStudent)));
        return ladokStudents;
    }

    public void findAktivitet(){
        System.out.println("Hello ===================================================================================");

        LadokKurstillfalle kurstillfalle = courseInformationServiceClient.findKurstillfalleByUid("a7e8fa5b-263b-11e9-8911-9699effc4236");

        String kod = kurstillfalle.getInstitution().getKod();
        System.out.println(kod);

        Instant i = Instant.ofEpochSecond(1579965925L, 0);
        Date d = Date.from(i);
        System.out.println(d.toString());
        Instant is = Instant.ofEpochSecond(1569943525L, 0);
        Date ds = Date.from(is);
        System.out.println(ds.toString());

        List<LadokKurstillfalle> suT = courseInformationServiceClient.findByEndDateInPeriod("305", ds, d);

        suT.forEach(System.out::println);


    }

    public List<LadokStudent> findStudents(String ladokId) {
        return getFromLadok(ladokId);
    }
}

