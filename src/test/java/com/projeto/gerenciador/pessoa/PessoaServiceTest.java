package com.projeto.gerenciador.pessoa;

import com.projeto.gerenciador.Models.Entities.Pessoa;
import com.projeto.gerenciador.Models.exceptions.NotFoundException;
import com.projeto.gerenciador.repositories.PessoaRepository;
import com.projeto.gerenciador.services.PessoaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class PessoaServiceTest {

    @InjectMocks
    private PessoaService pessoaService;

    @Mock
    private PessoaRepository pessoaRepository;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    public void getPessoaByIdThrowsExceptionWhenNotFound() {
        when(pessoaRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> pessoaService.getPessoaById(1L));
    }

    @Test
    public void adicionarPessoaAddsPessoa() {
        Pessoa pessoa = new Pessoa();
        when(pessoaRepository.save(pessoa)).thenReturn(pessoa);

        assertEquals(pessoa, pessoaService.adicionarPessoa(pessoa));
    }

    @Test
    public void adicionarPessoaThrowsExceptionWhenSaveFails() {
        Pessoa pessoa = new Pessoa();
        LocalDate dataNascimento = LocalDate.of(2000, 1, 1);
        pessoa.setDataNascimento(dataNascimento);
        pessoa.setNomeCompleto("Teste");
        when(pessoaRepository.save(pessoa)).thenThrow(new RuntimeException());

        assertThrows(RuntimeException.class, () -> pessoaService.adicionarPessoa(pessoa));
    }

    @Test
    public void getPessoasReturnsAllPessoas() {
        Pessoa pessoa1 = new Pessoa();
        Pessoa pessoa2 = new Pessoa();
        when(pessoaRepository.findAll()).thenReturn(Arrays.asList(pessoa1, pessoa2));

        assertEquals(2, pessoaService.getPessoas().size());
    }

    @Test
    public void getPessoaByIdReturnsCorrectPessoa() {
        Pessoa pessoa = new Pessoa();
        when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));

        assertEquals(pessoa, pessoaService.getPessoaById(1L));
    }

    @Test
    public void atualizarPessoaUpdatesPessoa() {
        Pessoa pessoa = new Pessoa();
        LocalDate dataNascimento = LocalDate.of(2000, 1, 1);
        pessoa.setDataNascimento(dataNascimento);
        pessoa.setNomeCompleto("Teste");
        when(pessoaRepository.saveAndFlush(pessoa)).thenReturn(pessoa);

        assertEquals(pessoa, pessoaService.atualizarPessoa(pessoa));
    }

    @Test
    public void atualizarPessoaThrowsExceptionWhenUpdateFails() {
        Pessoa pessoa = new Pessoa();
        when(pessoaRepository.saveAndFlush(pessoa)).thenThrow(new RuntimeException());

        assertThrows(RuntimeException.class, () -> pessoaService.atualizarPessoa(pessoa));
    }

    @Test
    public void removerPessoaRemovesPessoa() {
        doNothing().when(pessoaRepository).deleteById(1L);

        pessoaService.removerPessoa(1L);

        verify(pessoaRepository, times(1)).deleteById(1L);
    }

    @Test
    public void removerPessoaThrowsExceptionWhenRemovalFails() {
        doThrow(new RuntimeException()).when(pessoaRepository).deleteById(1L);

        assertThrows(RuntimeException.class, () -> pessoaService.removerPessoa(1L));
    }
}