package br.com.willbigas.primeiroprojetospringmvc.controller;

import br.com.willbigas.primeiroprojetospringmvc.model.Pessoa;
import br.com.willbigas.primeiroprojetospringmvc.model.Telefone;
import br.com.willbigas.primeiroprojetospringmvc.repository.PessoaRepository;
import br.com.willbigas.primeiroprojetospringmvc.repository.TelefoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TelefoneController {

    @Autowired
    PessoaRepository pessoaRepository;

    @Autowired
    TelefoneRepository telefoneRepository;


    @GetMapping(path = "/listartelefones/{idpessoa}")
    public ModelAndView buscarTelefonesDaPessoa(@PathVariable("idpessoa") Integer idPessoa) {

        ModelAndView modelAndView = new ModelAndView("cadastro/telefones");
        modelAndView.addObject("pessoa", pessoaRepository.findById(idPessoa).get());
        return modelAndView;
    }

    @PostMapping(path = "/adicionartelefonepessoa/{idpessoa}")
    public ModelAndView adicionarTelefonesDaPessoa
            (@Valid Telefone telefone, @PathVariable("idpessoa") Integer idPessoa , BindingResult bindingResult) {

        ModelAndView modelAndView = new ModelAndView("cadastro/telefones");
        modelAndView.addObject("pessoa", pessoaRepository.findById(idPessoa).get());
        List<String> listErros = new ArrayList<>();
        if (bindingResult.hasErrors()) {
            for (ObjectError obj : bindingResult.getAllErrors()) {
                listErros.add(obj.getDefaultMessage());
            }
            modelAndView.addObject("erros" , listErros);
            modelAndView.addObject("telefone" , telefone);
            return modelAndView;
        }

        telefone.setPessoa(pessoaRepository.findById(idPessoa).get());
        telefoneRepository.save(telefone);
        return modelAndView;
    }

    @GetMapping(path = "/removertelefone/{idtelefone}")
    public ModelAndView excluirTelefoneDaPessoa(@PathVariable("idtelefone") Integer idTelefone) {

        Pessoa pessoaDoTelefone = telefoneRepository.findById(idTelefone).get().getPessoa();

        telefoneRepository.deleteById(idTelefone);
        ModelAndView modelAndView = new ModelAndView("cadastro/telefones");
        modelAndView.addObject("pessoa", pessoaRepository.findById(pessoaDoTelefone.getId()).get());
        return modelAndView;
    }
}
