package ams.model.entity;

import ams.enums.BudgetCode;
import ams.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
public class Class extends BaseEntity{


    private String className;

    private Status status;

    private Integer plannedTraineeNo;

    private Integer acceptedTraineeNo;//validate acceptedTraineeNo <= plannedTraineeNo

    private Integer actualTraineeNo;// validate actualTraineeNo <= acceptedTraineeNo

    private LocalDate expectedStartDate;//can validate expectedStartDate <= expectedEndDate

    private LocalDate expectedEndDate;

    private String location;

    private String detailLocation;

    @Enumerated(EnumType.STRING)
    private BudgetCode budgetCode;

    private Double estimatedBudget;

    private String classAdmin;

    private String learningPath;

    private String history;

    @OneToMany(mappedBy = "aClass", cascade = CascadeType.ALL)
    private Set<ClassBudget> classBudgets;

    @OneToMany(mappedBy = "aClass", cascade = CascadeType.ALL)
    private Set<ClassAudit> classAudits;

    @OneToOne(cascade=CascadeType.ALL)
    @PrimaryKeyJoinColumn //This is added to avoid multiple primary key exception
    private ClassDetail classDetail;











}
