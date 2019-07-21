package br.com.willbigas.primeiroprojetospringmvc.repository;

import br.com.willbigas.primeiroprojetospringmvc.model.Telefone;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface TelefoneRepository extends PagingAndSortingRepository<Telefone, Integer> {


}
