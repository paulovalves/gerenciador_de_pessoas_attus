package com.projeto.gerenciador.controllers;

import com.projeto.gerenciador.Models.Entities.Endereco;
import com.projeto.gerenciador.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private PessoaService pessoaService;

}
