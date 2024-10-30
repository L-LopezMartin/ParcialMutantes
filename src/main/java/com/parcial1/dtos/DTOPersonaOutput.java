package com.parcial1.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DTOPersonaOutput {
    private Long id;
    private String[] genoma;
    private boolean mutant;
}
