package com.excella.reactor.domain;

import lombok.Value;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Value
@Embeddable
public class EmployeeSkillKey {
    @Column(name = "employee_id") Long employeeId;
    @Column(name = "skill_id") Long skillId;
}
