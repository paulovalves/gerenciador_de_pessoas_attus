package com.projeto.gerenciador.services;

import com.projeto.gerenciador.Models.Entities.Endereco;
import com.projeto.gerenciador.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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


    public List<Endereco> getTodosEnderecos() {
        try {
            return enderecoRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar todos os enderecos");
        }
    }

    public List<Endereco> getEnderecoPorCep(String cep) {
        try {
            return enderecoRepository.findByCep(cep);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar endereco por cep");
        }
    }

    public Endereco getEnderecoPorId(Long id) {
        try {
            return enderecoRepository.findById(id).get();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar endereco por id");
        }
    }

    @Transactional
    public Endereco adicionarEndereco(Endereco endereco) {
        try {
            return enderecoRepository.save(endereco);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao adicionar endereco");
        }
    }

    @Transactional
    public Endereco atualizarEndereco(Endereco endereco) {
        try {
            return enderecoRepository.save(endereco);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar endereco");
        }
    }
}
