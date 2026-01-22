package com.yusuf.fitnessmanagement.enrollment;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EnrollmentId implements Serializable {

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "fitness_class_id")
    private Long fitnessClassId;

    public EnrollmentId() {
    }

    public EnrollmentId(Long memberId, Long fitnessClassId) {
        this.memberId = memberId;
        this.fitnessClassId = fitnessClassId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getFitnessClassId() {
        return fitnessClassId;
    }

    public void setFitnessClassId(Long fitnessClassId) {
        this.fitnessClassId = fitnessClassId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EnrollmentId)) return false;
        EnrollmentId that = (EnrollmentId) o;
        return Objects.equals(memberId, that.memberId) &&
                Objects.equals(fitnessClassId, that.fitnessClassId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId, fitnessClassId);
    }
}
