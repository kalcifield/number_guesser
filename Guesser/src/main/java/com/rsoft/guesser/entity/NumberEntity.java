package com.rsoft.guesser.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.Instant;

@Data
@Entity
@Table(name = "number_entity")
public class NumberEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @CreatedDate
    @Column(name = "creation_date", updatable = false)
    private Instant creationDate;

    private Integer value;

    @Column(name = "solution_time_seconds")
    private Long solutionTimeSeconds;

    @PrePersist
    protected void prePersist() {
        if(this.creationDate == null) creationDate = Instant.now();
    }

}
