package com.excella.reactor.domain;

import java.util.Set;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import lombok.Value;
import lombok.experimental.Wither;
import org.springframework.data.annotation.Id;

@Value
@Entity
public class Employee implements DomainModel {
  @Wither @Id private Long id;

  @Embedded private Bio bio;

  @Embedded private Contact contact;

  @OneToMany(mappedBy = "skill")
  Set<EmployeeSkill> skills;
}
