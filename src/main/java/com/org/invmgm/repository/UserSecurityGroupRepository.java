package com.org.invmgm.repository;

import com.org.invmgm.model.UserSecurityGroup;
import com.org.invmgm.model.key.UserSecurityGroupAppliedId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSecurityGroupRepository extends JpaRepository<UserSecurityGroup, UserSecurityGroupAppliedId> {
    @Query("""
       SELECT usg
       FROM UserSecurityGroup usg
       WHERE usg.user.id = :userId
       AND usg.id.fromDate <= CURRENT_TIMESTAMP
       AND (usg.thruDate IS NULL OR usg.thruDate > CURRENT_TIMESTAMP)
       """)
    List<UserSecurityGroup> findActiveGroupsByUserId(Long userId);}
