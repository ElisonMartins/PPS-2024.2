package br.com.cookingbook.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cookingbook.demo.entities.Preparo;

public interface PreparoRepository extends JpaRepository<Preparo, Long> {
}
