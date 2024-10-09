package br.com.cookingbook.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Preparo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long preparo_id;
    private String tipo;
    private String descricao;
    @ManyToOne
    @JoinColumn(name = "receita_id")
    private Receita receita;
    
}
