package br.com.cookingbook.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cookingbook.demo.entities.Receita;

public interface ReceitaRepository extends JpaRepository<Receita, Long>{
    
}
