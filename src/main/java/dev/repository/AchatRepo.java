package dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.model.Achat;

public interface AchatRepo extends JpaRepository<Achat, Long> {

}
