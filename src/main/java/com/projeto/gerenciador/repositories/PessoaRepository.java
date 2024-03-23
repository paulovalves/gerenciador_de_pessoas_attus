package com.projeto.gerenciador.repositories;

import com.projeto.gerenciador.Models.Entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
