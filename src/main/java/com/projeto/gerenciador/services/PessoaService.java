package com.projeto.gerenciador.services;

import com.projeto.gerenciador.Models.Entities.Pessoa;
import com.projeto.gerenciador.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private EnderecoService enderecoService;

    public PessoaService(PessoaRepository pessoaRepository, EnderecoService enderecoService) {
        this.pessoaRepository = pessoaRepository;
        this.enderecoService = enderecoService;
    }

    public PessoaService() {
    }

    public List<Pessoa> getPessoas() {
        try {
            return pessoaRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar pessoas");
        }
    }

    public Pessoa getPessoaById(Long id) {
        try {
            return pessoaRepository.findById(id).orElse(null);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar pessoa");
        }
    }

    public Pessoa adicionarPessoa(@RequestBody Pessoa pessoa) {
        try {
            var endereco = pessoa.getEnderecos();
            var response = pessoaRepository.save(pessoa);

            if(endereco != null) {
                for (var end : endereco) {
                    end.setPessoa(response);
                    enderecoService.adicionarEndereco(end);
                }
            }

            return response;


        } catch (Exception e) {
            throw new RuntimeException("Erro ao adicionar pessoa");
        }
    }


}
