package com.example.kommunalexam.repositories;

import com.example.kommunalexam.models.Party;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartiesRepository extends JpaRepository<Party, Long> {
}
