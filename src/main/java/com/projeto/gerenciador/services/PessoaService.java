package com.projeto.gerenciador.services;

import com.projeto.gerenciador.Models.Entities.Pessoa;
import com.projeto.gerenciador.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Classe de serviço para manipulação de entidades Pessoa.
 */
@Service
public class PessoaService {

    /**
     * Repositório para acesso às entidades Pessoa no banco de dados.
     */
    @Autowired
    private PessoaRepository pessoaRepository;

    /**
     * Serviço para manipulação de entidades Endereco.
     */
    @Autowired
    private EnderecoService enderecoService;

    /**
     * Construtor para criar uma nova instância de PessoaService com o PessoaRepository e EnderecoService fornecidos.
     *
     * @param pessoaRepository O repositório para acesso às entidades Pessoa no banco de dados.
     * @param enderecoService O serviço para manipulação de entidades Endereco.
     */
    public PessoaService(PessoaRepository pessoaRepository, EnderecoService enderecoService) {
        this.pessoaRepository = pessoaRepository;
        this.enderecoService = enderecoService;
    }

    /**
     * Construtor padrão para criar uma nova instância de PessoaService.
     */
    public PessoaService() {
    }

    /**
     * Recupera todas as entidades Pessoa do banco de dados.
     *
     * @return Uma lista de todas as entidades Pessoa.
     * @throws RuntimeException se ocorrer algum erro durante o processo.
     */
    public List<Pessoa> getPessoas() {
        try {
            return pessoaRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar pessoas");
        }
    }

    /**
     * Recupera uma entidade Pessoa pelo seu ID.
     *
     * @param id O ID da entidade Pessoa a ser recuperada.
     * @return A entidade Pessoa com o ID fornecido, ou null se tal entidade não existir.
     * @throws RuntimeException se ocorrer algum erro durante o processo.
     */
    public Pessoa getPessoaById(Long id) {
        try {
            return pessoaRepository.findById(id).orElse(null);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar pessoa");
        }
    }

    /**
     * Adiciona uma nova entidade Pessoa ao banco de dados.
     *
     * @param pessoa A entidade Pessoa a ser adicionada.
     * @return A entidade Pessoa adicionada.
     * @throws RuntimeException se ocorrer algum erro durante o processo.
     */
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

    /**
     * Atualiza uma entidade Pessoa no banco de dados.
     *
     * @param pessoa A entidade Pessoa a ser atualizada.
     * @return A entidade Pessoa atualizada.
     * @throws RuntimeException se ocorrer algum erro durante o processo.
     */
    public Pessoa atualizarPessoa(@RequestBody Pessoa pessoa) {
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
            throw new RuntimeException("Erro ao atualizar pessoa");
        }
    }
}