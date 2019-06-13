package com.excella.reactor.domain;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "employee_skill")
public class EmployeeSkill implements Serializable {

  @Id
  @ManyToOne
  @JoinColumn(name = "employee_id", referencedColumnName = "id")
  private Employee employee;

  @Id
  @ManyToOne
  @JoinColumn(name = "skill_id", referencedColumnName = "id")
  private Skill skill;

  @Enumerated(EnumType.STRING)
  private SkillProficiency proficiency;

  @Column(name = "is_primary")
  private boolean primary;
}
