package com.projeto.gerenciador.repositories;

import com.projeto.gerenciador.Models.Entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PessoaRepository extends JpaRepository<Pessoa, UUID> {
}
