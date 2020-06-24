package br.rifas.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Getter
@Setter
public class Premio {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name = "id_premio", updatable = false)
    private Long id;

    private String descricao;

    private int nuPremio;

    private String detalhe1;

    private String detalhe2;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dtSorteio;

    private Double valor;

    @OneToMany(mappedBy = "premio", fetch = FetchType.EAGER)
    private List<Numeros> numeros = new ArrayList<>();

    private String numeroSorteado;

    private String cpfGanhador;
}
