package br.rifas.dto;

import br.rifas.model.Numeros;
import br.rifas.model.enums.TipoStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NumerosDTO {
    private Long id;

    private String numero;

    private String status;

    private Date dtReserva;

    private CompradorDTO comprador;

    public NumerosDTO() {};

    public NumerosDTO(Numeros numeros) {
        this.id = numeros.getId();
        this.numero = numeros.getNumero();
        this.status = numeros.getStatus().isEmpty() ? TipoStatus.LIVRE.value : numeros.getStatus();
        this.dtReserva = numeros.getDtReserva();
        if (numeros.getComprador() != null) {
            this.comprador = new CompradorDTO(numeros.getComprador());
        }
    }

    public Numeros toEntity() {
        Numeros numeros = new Numeros();
        numeros.setId(this.id);
        numeros.setNumero(this.numero);
        numeros.setStatus(this.status.toString());
        numeros.setDtReserva(this.dtReserva);
        numeros.setComprador(this.comprador == null ? null : this.comprador.toEntity());

        return numeros;
    }
}
