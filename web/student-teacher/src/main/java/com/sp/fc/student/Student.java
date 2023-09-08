package com.sp.fc.student;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

    private String id;
    private String username;

    @JsonIgnore
    private Set<GrantedAuthority> role;

    private String teacherId;

}
