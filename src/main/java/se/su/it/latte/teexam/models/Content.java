
package se.su.it.latte.teexam.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
@SuppressWarnings("unused")
public class Content {

    @JsonAlias(value="campus")
    private String campus;
    // TODO: Update type when value is present
    @JsonAlias(value="checklist")
    private List<Object> checklist;
    // TODO: Update type when value is present
    @JsonAlias(value="@children")
    private List<Object> children;
    @JsonAlias(value="cost_center_ids")
    private List<String> costCenterIds;
    @JsonAlias(value="course_event_ids")
    private List<Object> courseEventIds;
    @JsonAlias(value="course_ids")
    private List<String> courseIds;
    @JsonAlias(value="created_at")
    private long createdAt;
    @JsonAlias(value="deleted_at")
    private long deletedAt;
    @JsonAlias(value="department_ids")
    private List<String> departmentIds;
    @JsonAlias(value="exam_target_date")
    private long examTargetDate;
    @JsonAlias(value="exam_time")
    private long examTime;
    @JsonAlias(value="@exam_type")
    private ExamType examType;
    @JsonAlias(value="exam_type_id")
    private long examTypeId;
    @JsonAlias(value="id")
    private long id;
    @JsonAlias(value="information_to_administrators")
    private String informationToAdministrators;
    @JsonAlias(value= "information_to_students")
    private String informationToStudents;
    @JsonAlias(value="information_to_supervisors")
    private Object informationToSupervisors;
    @JsonAlias(value="is_re_exam")
    private Boolean isReExam;
    @JsonAlias(value="is_requested")
    private Boolean isRequested;
    @JsonAlias(value="is_scheduled")
    private Boolean isScheduled;
    @JsonAlias(value="@language")
    private Language language;
    @JsonAlias(value="language_id")
    private long languageId;
    @JsonAlias(value="linked_exam_id")
    private long linkedExamId;
    @JsonAlias(value="module_ids")
    private List<String> moduleIds;
    @JsonAlias(value="name")
    private String name;
    @JsonAlias(value="organization_id")
    private String organizationId;
    @JsonAlias(value="owner_id")
    private String ownerId;
    @JsonAlias(value="parent_id")
    private String parentId;
    @JsonAlias(value="post_exam_notes")
    private Object postExamNotes;
    @JsonAlias(value="professor_ids")
    private List<String> professorIds;
    @JsonAlias(value="publication_booking_id")
    private String publicationBookingId;
    @JsonAlias(value="publication_date")
    private Long publicationDate;
    @JsonAlias(value="publication_status")
    private String publicationStatus;
    @JsonAlias(value="publication_user_id")
    private String publicationUserId;
    @JsonAlias(value="scheduled_end_seat_no")
    private long scheduledEndSeatNo;
    @JsonAlias(value="scheduled_end_time")
    private long scheduledEndTime;
    @JsonAlias(value="scheduled_exam_slot_id")
    private long scheduledExamSlotId;
    @JsonAlias(value="scheduled_room_booking_id")
    private long scheduledRoomBookingId;
    @JsonAlias(value= "scheduled_room_id")
    private long scheduledRoomId;
    @JsonAlias(value="scheduled_start_seat_no")
    private long scheduledStartSeatNo;
    @JsonAlias(value="scheduled_start_time")
    private long scheduledStartTime;
    @JsonAlias(value="students_allocated")
    private long studentsAllocated;
    @JsonAlias(value="students_planned")
    private long studentsPlanned;
    @JsonAlias(value="students_registered")
    private long studentsRegistered;
    @JsonAlias(value="updated_at")
    private long updatedAt;

}
