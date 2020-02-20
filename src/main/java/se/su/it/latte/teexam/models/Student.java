package se.su.it.latte.teexam.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Student {

    @JsonAlias(value="exam_id")
    @JsonProperty(value="exam_id")
    private String examId;
    @JsonAlias(value="student_id")
    @JsonProperty(value="student_id")
    private String studentId;
    @JsonAlias(value="student_name")
    @JsonProperty(value="student_name")
    private String studentName;
    @JsonAlias(value="student_anonymized_code")
    @JsonProperty(value="student_anonymized_code")
    private String studentAnonymizedCode;
    @JsonAlias(value="student_personal_identification_number")
    @JsonProperty(value="student_personal_identification_number")
    private String studentPersonalIdentificationNumber;
    @JsonAlias(value="registration_Date")
    @JsonProperty(value="registration_Date")
    private String registrationDate;
    @JsonAlias(value="assigned_special_need")
    @JsonProperty(value="assigned_special_need")
    private String assighedSpecialNeed;
    @JsonAlias(value="external_exam_name")
    @JsonProperty(value="external_exam_name")
    private String externalExamName;
    @JsonAlias(value="imported_special_needs_assignment")
    @JsonProperty(value="imported_special_needs_assignment")
    private String importedSpecialNeecsAssignment;
}
