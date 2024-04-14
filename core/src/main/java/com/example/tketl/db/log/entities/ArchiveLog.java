package com.example.tketl.db.log.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "archiveLog")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArchiveLog {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    private String errorMessage;
    @Column(nullable = false)
    public String sourceType;
    public String destinationType;
    @Column(nullable = false)
    private String source;
    private String destination;

    private String creator;

    @Column(nullable = false)
    private int status;

    private String originalFileName;

    @PrePersist
    private void insert(){
        creationDate = new Date();
    }

}