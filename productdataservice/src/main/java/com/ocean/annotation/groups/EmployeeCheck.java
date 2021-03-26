package com.ocean.annotation.groups;

import javax.validation.GroupSequence;

@GroupSequence({NameCheck.class,AgeCheck.class})
public interface EmployeeCheck {
}
