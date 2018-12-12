package dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.model.Caracteristiques;

public interface CaracteristiquesRepo extends JpaRepository<Caracteristiques, Long> {

}
