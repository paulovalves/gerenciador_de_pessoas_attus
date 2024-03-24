package com.projeto.gerenciador.services;

import com.projeto.gerenciador.Models.Entities.Endereco;
import com.projeto.gerenciador.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public EnderecoService() {
    }

    @Transactional
    public Endereco adicionarEndereco(Endereco endereco) {
        try {
            return enderecoRepository.save(endereco);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao adicionar endereco");
        }
    }
}
