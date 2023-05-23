package ams.model.entity;

import ams.enums.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class ClassDetail extends BaseEntity{


    @Enumerated(EnumType.STRING)
    private SubjectType subjectType;

    @Enumerated(EnumType.STRING)
    private SubSubjectType subSubjectType;

    @Enumerated(EnumType.STRING)
    private DeliveryType deliveryType;

    @Enumerated(EnumType.STRING)
    private FormatType formatType;

    @Enumerated(EnumType.STRING)
    private Scope scope;

    @Column
    private String supplier;

    @Column
    private LocalDate actualStartDate;//validate actualStartDate< actualEndDate

    @Column
    private LocalDate actualEndDate;

    @Column
    private String masterTrainer;
    //Cho nay chua co thong tin ???

    @Column
    private String trainer;

    @Column
    private String curriculum;
    //TODO: cho nay import file excel

    @Column
    private String remarks;



}
