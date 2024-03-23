package com.projeto.gerenciador.controllers;

import com.projeto.gerenciador.Models.Entities.Pessoa;
import com.projeto.gerenciador.services.PessoaService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoaController extends ApiController {

    @Autowired
    private PessoaService pessoaService;
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

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Pessoa> buscarPessoa(@PathParam("id") Long id) {
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

    @PostMapping("/adicionar")
    public ResponseEntity<Pessoa> adicionarPessoa(@RequestBody @Valid Pessoa pessoa) {
        try {
            var response = pessoaService.adicionarPessoa(pessoa);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao adicionar pessoa");
        }
    }



}
