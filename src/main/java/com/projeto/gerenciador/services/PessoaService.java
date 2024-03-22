package com.projeto.gerenciador.services;

import com.projeto.gerenciador.Models.Entities.Pessoa;
import com.projeto.gerenciador.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public PessoaService() {
    }

    public List<Pessoa>  getPessoas() {
        try {
            return pessoaRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar pessoas");
        }
    }




}
