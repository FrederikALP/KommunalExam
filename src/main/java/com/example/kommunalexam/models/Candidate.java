package com.example.kommunalexam.models;

import lombok.Data;
import org.springframework.lang.Nullable;
import javax.persistence.*;

@Data
@Table(name="candidates")
@Entity
public class Candidate {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "party_id")
    @Nullable
    private Party party;

}