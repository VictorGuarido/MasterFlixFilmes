package br.com.mastertech.masterflix.MasterFlix.repository;

import br.com.mastertech.masterflix.MasterFlix.model.Filme;
import org.springframework.data.repository.CrudRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface FilmeRepository extends CrudRepository<Filme, Long> {

    Optional<Filme> findByNome(String nome);
}
