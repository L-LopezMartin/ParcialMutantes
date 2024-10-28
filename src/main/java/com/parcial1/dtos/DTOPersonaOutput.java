package com.parcial1.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DTOPersonaOutput {
    private Long id;
    private String[] genoma;
    private boolean mutant;
}
