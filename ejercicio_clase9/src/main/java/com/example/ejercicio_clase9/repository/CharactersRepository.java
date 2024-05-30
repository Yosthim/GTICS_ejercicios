package com.example.ejercicio_clase9.repository;

import com.example.ejercicio_clase9.entity.Characters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharactersRepository extends JpaRepository<Characters, Integer> {
}
