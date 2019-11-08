package com.med.library.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Penalty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Penalty reason is mandatory")
    private String reason;

    @NotNull(message = "Penalty duration is mandatory")
    private int duration;

    private Date startDate;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public Penalty(@NotNull(message = "Penalty reason is mandatory") String reason, @NotNull(message = "Penalty duration is mandatory") int duration, Date startDate, Member member) {
        this.reason = reason;
        this.duration = duration;
        this.startDate = startDate;
        this.member = member;
    }
}
