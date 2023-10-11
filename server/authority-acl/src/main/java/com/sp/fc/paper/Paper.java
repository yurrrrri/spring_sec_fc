package com.sp.fc.paper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Paper {

    @Id
    private Long id;
    private String title;
    private String tutorId;
    private State state;

    public static enum State {
        PREPARE, READY, END
    }
}
