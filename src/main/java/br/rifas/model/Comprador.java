package br.rifas.model;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Comprador {

    @Id
    private String cpf;

    private String nome;

    private String telefone;

    @OneToMany(mappedBy = "comprador")
    private Set<Numeros> numeros = new HashSet<>();

}
