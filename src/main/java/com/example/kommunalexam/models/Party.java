package com.example.kommunalexam.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.util.Set;

@Data
@Table(name="parties")
@Entity
public class Party {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column
    private String name;

    @Column
    private int partyVotes;

    @JsonIgnore
    @OneToMany(mappedBy = "party", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Candidate> candidates;
}