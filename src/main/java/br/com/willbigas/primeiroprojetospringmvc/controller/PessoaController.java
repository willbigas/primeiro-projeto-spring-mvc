package br.com.willbigas.primeiroprojetospringmvc.controller;

import br.com.willbigas.primeiroprojetospringmvc.model.Pessoa;
import br.com.willbigas.primeiroprojetospringmvc.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Controller
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @GetMapping(path = "/cadastropessoa")
    public ModelAndView listarPessoas() {
        ModelAndView modelAndView = new ModelAndView("cadastro/cadastroPessoa");

        modelAndView.addObject("pessoas", pessoaRepository.findAll());
        modelAndView.addObject("pessoa", new Pessoa());
        return modelAndView;
    }

    @PostMapping(path = "/salvarpessoa")
    public ModelAndView cadastrar(@Valid Pessoa pessoa , BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("cadastro/cadastroPessoa");
            modelAndView.addObject("pessoas", pessoaRepository.findAll());
            modelAndView.addObject("pessoa", pessoa);
            List<String> listErros = new ArrayList<>();

            for (ObjectError obj : bindingResult.getAllErrors()) {
                listErros.add(obj.getDefaultMessage());
            }
            modelAndView.addObject("erros" , listErros);
            return modelAndView;
        }

        pessoaRepository.save(pessoa);
        ModelAndView modelAndView = new ModelAndView("cadastro/cadastroPessoa");
        modelAndView.addObject("pessoas", pessoaRepository.findAll());
        modelAndView.addObject("pessoa", new Pessoa());
        return modelAndView;
    }

    @GetMapping(path = "/editarpessoa/{idpessoa}")
    public ModelAndView editar(@PathVariable("idpessoa") Integer idPessoa) {

        ModelAndView modelAndView = new ModelAndView("cadastro/cadastroPessoa");
        modelAndView.addObject("pessoa", pessoaRepository.findById(idPessoa).get());
        return modelAndView;
    }


    @GetMapping(path = "/removerpessoa/{idpessoa}")
    public ModelAndView excluir(@PathVariable("idpessoa") Integer idPessoa) {

        pessoaRepository.deleteById(idPessoa);
        ModelAndView modelAndView = new ModelAndView("cadastro/cadastroPessoa");
        modelAndView.addObject("pessoas", pessoaRepository.findAll());
        modelAndView.addObject("pessoa", new Pessoa());
        return modelAndView;
    }

    @PostMapping(path = "/pesquisarpessoa")
    public ModelAndView pesquisar(@RequestParam("campopesquisa") String campoPesquisa) {
        ModelAndView modelAndView = new ModelAndView("cadastro/cadastroPessoa");
        modelAndView.addObject("pessoas",
                pessoaRepository.findPessoasByNomeContainingIgnoreCaseOrSobrenomeContainingIgnoreCase(campoPesquisa , campoPesquisa));
        modelAndView.addObject("pessoa", new Pessoa());
        return modelAndView;
    }


}
