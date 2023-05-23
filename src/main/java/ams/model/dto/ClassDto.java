package ams.model.dto;

import ams.enums.BudgetCode;
import ams.enums.Status;
import ams.model.entity.ClassAudit;
import ams.model.entity.ClassBudget;
import ams.model.entity.ClassDetail;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class ClassDto extends BaseResponseDto{
    private String className;

    private Status status;

    private Integer plannedTraineeNo;

    private Integer acceptedTraineeNo;//validate acceptedTraineeNo <= plannedTraineeNo

    private Integer actualTraineeNo;// validate actualTraineeNo <= acceptedTraineeNo

    private LocalDate expectedStartDate;//can validate expectedStartDate <= expectedEndDate

    private LocalDate expectedEndDate;

    private String location;

    private String detailLocation;


    private BudgetCode budgetCode;

    private Double estimatedBudget;

    private String classAdmin;

    private String learningPath;

    private String history;

}
