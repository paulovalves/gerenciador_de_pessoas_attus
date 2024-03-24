package com.projeto.gerenciador.controllers;

import com.projeto.gerenciador.Models.Entities.Pessoa;
import com.projeto.gerenciador.services.PessoaService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para manipulação de entidades Pessoa.
 */
@RestController
@RequestMapping("/pessoa")
public class PessoaController extends ApiController {

    /**
     * Serviço para manipulação de entidades Pessoa.
     */
    @Autowired
    private PessoaService pessoaService;

    /**
     * Manipula a requisição GET para listar todas as entidades Pessoa.
     *
     * @return ResponseEntity contendo a lista de entidades Pessoa e o código de status HTTP.
     * @throws RuntimeException se ocorrer algum erro durante o processo.
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna a lista de pessoas"),
            @ApiResponse(responseCode = "204", description = "Não há pessoas cadastradas")
    })
    @GetMapping("/listar")
    public ResponseEntity<List<Pessoa>> listarPessoas() {
        try {
            var response = pessoaService.getPessoas();
            if (response.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar pessoas");
        }
    }

    /**
     * Manipula a requisição GET para buscar uma entidade Pessoa pelo seu ID.
     *
     * @param id O ID da entidade Pessoa a ser recuperada.
     * @return ResponseEntity contendo a entidade Pessoa e o código de status HTTP.
     * @throws RuntimeException se ocorrer algum erro durante o processo.
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna a pessoa"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro ao buscar pessoa")
    })
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Pessoa> buscarPessoa(@PathVariable("id") Long id) {
        try {
            var response = pessoaService.getPessoaById(id);
            if (response == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar pessoa");
        }
    }

    /**
     * Manipula a requisição POST para adicionar uma nova entidade Pessoa.
     *
     * @param pessoa A entidade Pessoa a ser adicionada.
     * @return ResponseEntity contendo a entidade Pessoa adicionada e o código de status HTTP.
     * @throws RuntimeException se ocorrer algum erro durante o processo.
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna a pessoa adicionada"),
            @ApiResponse(responseCode = "400", description = "Erro ao adicionar pessoa"),
            @ApiResponse(responseCode = "500", description = "Erro ao adicionar pessoa")
    })
    @PostMapping("/adicionar")
    public ResponseEntity<Pessoa> adicionarPessoa(@RequestBody @Valid Pessoa pessoa) {
        try {
            var response = pessoaService.adicionarPessoa(pessoa);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao adicionar pessoa");
        }
    }

    /**
     * Manipula a requisição PUT para atualizar uma entidade Pessoa existente.
     *
     * @param pessoa A entidade Pessoa a ser atualizada.
     * @return ResponseEntity contendo a entidade Pessoa atualizada e o código de status HTTP.
     * @throws RuntimeException se ocorrer algum erro durante o processo.
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna a pessoa atualizada"),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar pessoa"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro ao atualizar pessoa")
    })
    @PutMapping("/atualizar")
    public ResponseEntity<Pessoa> atualizarPessoa(@RequestBody @Valid Pessoa pessoa) {
        try {
            var response = pessoaService.atualizarPessoa(pessoa);
            if(response == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar pessoa");
        }
    }

    /**
     * Manipula a requisição DELETE para deletar uma entidade Pessoa pelo seu ID.
     *
     * @param id O ID da entidade Pessoa a ser deletada.
     * @return ResponseEntity contendo o código de status HTTP.
     * @throws RuntimeException se ocorrer algum erro durante o processo.
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoa deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro ao deletar pessoa")
    })
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarPessoa(@PathVariable ("id") Long id) {
        try {
            pessoaService.removerPessoa(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar pessoa");
        }
    }
}
