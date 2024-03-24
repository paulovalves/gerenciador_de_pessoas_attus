package com.projeto.gerenciador.pessoa;

import com.projeto.gerenciador.Models.Entities.Pessoa;
import com.projeto.gerenciador.controllers.PessoaController;
import com.projeto.gerenciador.services.PessoaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class PessoaControllerTest {

    @InjectMocks
    private PessoaController pessoaController;

    @Mock
    private PessoaService pessoaService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void listarPessoasReturnsAllPessoas() {
        Pessoa pessoa = new Pessoa();
        when(pessoaService.getPessoas()).thenReturn(Collections.singletonList(pessoa));

        ResponseEntity<List<Pessoa>> response = pessoaController.listarPessoas();

        assertEquals(1, Objects.requireNonNull(response.getBody()).size());
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void listarPessoasReturnsNoContentWhenNoPessoas() {
        when(pessoaService.getPessoas()).thenReturn(Collections.emptyList());

        ResponseEntity<List<Pessoa>> response = pessoaController.listarPessoas();

        assertEquals(204, response.getStatusCode().value());
    }

    @Test
    public void buscarPessoaReturnsCorrectPessoa() {
        Pessoa pessoa = new Pessoa();
        when(pessoaService.getPessoaById(1L)).thenReturn(pessoa);

        ResponseEntity<Pessoa> response = pessoaController.buscarPessoa(1L);

        assertEquals(pessoa, response.getBody());
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void buscarPessoaReturnsNotFoundWhenPessoaDoesNotExist() {
        when(pessoaService.getPessoaById(1L)).thenReturn(null);

        ResponseEntity<Pessoa> response = pessoaController.buscarPessoa(1L);

        assertEquals(404, response.getStatusCode().value());
    }

    @Test
    public void adicionarPessoaAddsPessoa() {
        Pessoa pessoa = new Pessoa();
        when(pessoaService.adicionarPessoa(pessoa)).thenReturn(pessoa);

        ResponseEntity<Pessoa> response = pessoaController.adicionarPessoa(pessoa);

        assertEquals(pessoa, response.getBody());
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void atualizarPessoaUpdatesPessoa() {
        Pessoa pessoa = new Pessoa();
        when(pessoaService.atualizarPessoa(pessoa)).thenReturn(pessoa);

        ResponseEntity<Pessoa> response = pessoaController.atualizarPessoa(pessoa);

        assertEquals(pessoa, response.getBody());
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void atualizarPessoaReturnsNotFoundWhenPessoaDoesNotExist() {
        Pessoa pessoa = new Pessoa();
        when(pessoaService.atualizarPessoa(pessoa)).thenReturn(null);

        ResponseEntity<Pessoa> response = pessoaController.atualizarPessoa(pessoa);

        assertEquals(404, response.getStatusCode().value());
    }

    @Test
    public void deletarPessoaDeletesPessoa() {
        doNothing().when(pessoaService).removerPessoa(1L);

        ResponseEntity<Void> response = pessoaController.deletarPessoa(1L);

        assertEquals(200, response.getStatusCode().value());
    }
}
