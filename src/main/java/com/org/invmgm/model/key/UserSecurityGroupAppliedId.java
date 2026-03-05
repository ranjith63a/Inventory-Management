package com.org.invmgm.model.key;

import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class UserSecurityGroupAppliedId implements Serializable {

    private Long userLoginId;
    private String groupId;
    private LocalDateTime fromDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserSecurityGroupAppliedId)) return false;
        UserSecurityGroupAppliedId that = (UserSecurityGroupAppliedId) o;
        return Objects.equals(userLoginId, that.userLoginId) &&
                Objects.equals(groupId, that.groupId) &&
                Objects.equals(fromDate, that.fromDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userLoginId, groupId, fromDate);
    }
}
