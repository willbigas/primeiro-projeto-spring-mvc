package br.com.willbigas.primeiroprojetospringmvc.repository;

import br.com.willbigas.primeiroprojetospringmvc.model.Pessoa;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface PessoaRepository extends PagingAndSortingRepository<Pessoa, Integer> {

    List<Pessoa> findPessoasByNomeContainingIgnoreCaseOrSobrenomeContainingIgnoreCase(String nome, String sobrenome);

}
