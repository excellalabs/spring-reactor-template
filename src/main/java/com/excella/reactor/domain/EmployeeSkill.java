package com.excella.reactor.domain;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "employee_skill")
public class EmployeeSkill {
  @EmbeddedId private EmployeeSkillKey id;

  @ManyToOne
  @MapsId("employeeId")
  @JoinColumn(name = "employee_id")
  private Employee employee;

  @ManyToOne
  @MapsId("skillId")
  @JoinColumn(name = "skill_id")
  private Skill skill;

  @Enumerated(EnumType.STRING)
  private SkillProficiency proficiency;

  @Column(name = "is_primary")
  private boolean primary;
}
