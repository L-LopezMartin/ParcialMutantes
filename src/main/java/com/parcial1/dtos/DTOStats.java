package com.parcial1.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DTOStats {
    int count_mutant_dna;
    int count_human_dna;
    float ratio;
}
