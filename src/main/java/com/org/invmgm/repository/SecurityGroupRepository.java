package com.org.invmgm.repository;

import com.org.invmgm.model.SecurityGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecurityGroupRepository extends JpaRepository<SecurityGroup, String> {
}
