package com.projeto.gerenciador.endereco;

import com.projeto.gerenciador.Models.Entities.Endereco;
import com.projeto.gerenciador.repositories.EnderecoRepository;
import com.projeto.gerenciador.services.EnderecoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class EnderecoServiceTest {

    @InjectMocks
    private EnderecoService enderecoService;

    @Mock
    private EnderecoRepository enderecoRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getTodosEnderecosReturnsAllEnderecos() {
        Endereco endereco1 = new Endereco();
        Endereco endereco2 = new Endereco();
        when(enderecoRepository.findAll()).thenReturn(Arrays.asList(endereco1, endereco2));

        assertEquals(2, enderecoService.getTodosEnderecos().size());
    }

    @Test
    public void getEnderecoPorCepReturnsCorrectEndereco() {
        Endereco endereco = new Endereco();
        when(enderecoRepository.findByCep("12345")).thenReturn(Collections.singletonList(endereco));

        assertEquals(endereco, enderecoService.getEnderecoPorCep("12345").get(0));
    }

    @Test
    public void getEnderecoPorIdReturnsCorrectEndereco() {
        Endereco endereco = new Endereco();
        when(enderecoRepository.findById(1L)).thenReturn(Optional.of(endereco));

        assertEquals(endereco, enderecoService.getEnderecoPorId(1L));
    }

    @Test
    public void getEnderecoPorIdThrowsExceptionWhenNotFound() {
        when(enderecoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> enderecoService.getEnderecoPorId(1L));
    }

    @Test
    public void adicionarEnderecoAddsEndereco() {
        Endereco endereco = new Endereco();
        when(enderecoRepository.save(endereco)).thenReturn(endereco);

        assertEquals(endereco, enderecoService.adicionarEndereco(endereco));
    }

    @Test
    public void adicionarEnderecoThrowsExceptionWhenSaveFails() {
        Endereco endereco = new Endereco();
        when(enderecoRepository.save(endereco)).thenThrow(new RuntimeException());

        assertThrows(RuntimeException.class, () -> enderecoService.adicionarEndereco(endereco));
    }

    @Test
    public void atualizarEnderecoUpdatesEndereco() {
        Endereco endereco = new Endereco();
        when(enderecoRepository.save(endereco)).thenReturn(endereco);

        assertEquals(endereco, enderecoService.atualizarEndereco(endereco));
    }

    @Test
    public void atualizarEnderecoThrowsExceptionWhenUpdateFails() {
        Endereco endereco = new Endereco();
        when(enderecoRepository.save(endereco)).thenThrow(new RuntimeException());

        assertThrows(RuntimeException.class, () -> enderecoService.atualizarEndereco(endereco));
    }
}
