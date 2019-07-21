package br.com.willbigas.primeiroprojetospringmvc.controller;

import br.com.willbigas.primeiroprojetospringmvc.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class IndexController {

    @Autowired
    PessoaRepository pessoaRepository;
    @GetMapping("/")
    public String index() {
        return "index";
    }
}
