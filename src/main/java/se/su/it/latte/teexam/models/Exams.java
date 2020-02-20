package se.su.it.latte.teexam.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Exams {

//    int id;
//    String name;
//    List<String> photoUrls;
    // TODO: Check later if this is going to be an enum, but for the time being we leave it as string because
    //  we dont have any other documentation
    ExamStatus status;
}
