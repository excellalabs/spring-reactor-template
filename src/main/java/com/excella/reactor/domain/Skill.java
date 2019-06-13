package com.excella.reactor.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import lombok.*;

/**
 * This class represents an immutable skill, as defined in the domain, as opposed to a skill
 * possessed by an employee.
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class Skill extends DomainModel {
  @Id private Long id;
  private String name;
  @Embedded private SkillCategory category;

  @OneToMany(mappedBy = "skill", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  List<EmployeeSkill> employees = new ArrayList<>();
}
