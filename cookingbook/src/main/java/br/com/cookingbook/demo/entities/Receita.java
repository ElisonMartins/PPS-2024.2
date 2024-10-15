package br.com.cookingbook.demo.entities;

import java.util.List;
import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Receita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long receita_id;

    private String titulo;
    private String descricao;

    @OneToMany(mappedBy = "receita", cascade = CascadeType.ALL) // Uma receita tem muitos ingredientes
    private List<Ingrediente> ingredientes;

    @OneToOne(cascade = CascadeType.ALL) // Uma receita tem um único preparo
    private Preparo preparo;

    private Date tempo;
    private int porcoes;
}
