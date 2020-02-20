
package se.su.it.latte.teexam.models;

import lombok.Data;

@Data
@SuppressWarnings("unused")
public class Row {

    private Object allocatedExamId;
    private Object assignedSpecialNeed;
    private long createdAt;
    private Object deletedAt;
    private long examId;
    private String externalExamName;
    private long id;
    private String importedSpecialNeedsAssignment;
    private Object postExamNotes;
    private long registrationDate;
    private Object seatingId;
    private Object seatingInfo;
    private String studentAnonymizedCode;
    private String studentId;
    private String studentName;
    private String studentPersonalIdentificationNumber;
    private long updatedAt;

}
