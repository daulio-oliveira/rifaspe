package br.rifas.controller.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseError {
    private List<String> erros = new ArrayList<>();
    private Integer status;
    private Long timestamp;

}
