package br.rifas.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Numeros {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private Long id;

    private String numero;

    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dtReserva;

    @ManyToOne
    private Comprador comprador;

    @ManyToOne
    @JoinColumn(name = "id_premio")
    private Premio premio;
}
