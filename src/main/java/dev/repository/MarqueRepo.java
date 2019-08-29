package dev.repository;

import dev.model.Marque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarqueRepo extends JpaRepository<Marque, Long> {
    Marque findByNom(String nom);
}