package br.com.cookingbook.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cookingbook.demo.entities.Ingrediente;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {
}
