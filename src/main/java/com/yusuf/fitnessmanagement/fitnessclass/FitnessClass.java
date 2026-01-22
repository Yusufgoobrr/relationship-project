package com.yusuf.fitnessmanagement.fitnessclass;

import com.yusuf.enums.Days;
import com.yusuf.enums.DifficultyLevel;
import com.yusuf.enums.MembershipTier;
import com.yusuf.fitnessmanagement.enrollment.Enrollment;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "fitness_class")
public class FitnessClass {

    @Id
    @SequenceGenerator(name = "fitness_id_seq", sequenceName = "fitness_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fitness_id_seq")
    private Long fitnessClassId;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String className;

    @Column(columnDefinition = "TEXT")
    private String description;

    @NotBlank
    @Column(nullable = false)
    private String instructorName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,name = "day_of_the_week")
    private Days dayOfTheWeek;

    @Column(nullable = false)
    private Time startTime;

    @Min(30)
    @Max(120)
    @Column(nullable = false)
    private Integer durationInMinutes;

    @Min(1)
    @Column(nullable = false)
    private Integer maxCapacity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DifficultyLevel difficultyLevel;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MembershipTier requiredMembershipTier;

    @OneToMany(
            mappedBy = "fitnessClass",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Enrollment> enrollments = new ArrayList<>();

    public FitnessClass() {
    }


    public void addEnrollment(Enrollment enrollment) {
        enrollments.add(enrollment);
        enrollment.setFitnessClass(this);
    }

    public void removeEnrollment(Enrollment enrollment) {
        enrollments.remove(enrollment);
        enrollment.setFitnessClass(null);
    }

    public Long getFitnessClassId() {
        return fitnessClassId;
    }

    public void setFitnessClassId(Long fitnessClassId) {
        this.fitnessClassId = fitnessClassId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public Days getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public void setDayOfTheWeek(Days dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Integer getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(Integer durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public Integer getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(Integer maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public MembershipTier getRequiredMembershipTier() {
        return requiredMembershipTier;
    }

    public void setRequiredMembershipTier(MembershipTier requiredMembershipTier) {
        this.requiredMembershipTier = requiredMembershipTier;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }
}
