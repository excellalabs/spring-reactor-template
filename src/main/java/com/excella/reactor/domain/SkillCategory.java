package com.excella.reactor.domain;

import java.io.Serializable;
import javax.persistence.Embeddable;
import lombok.Value;

@Embeddable
@Value
public class SkillCategory implements Serializable {
  private String name;
}
