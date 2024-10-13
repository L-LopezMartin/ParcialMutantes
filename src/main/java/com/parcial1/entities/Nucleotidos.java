package com.parcial1.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public enum Nucleotidos {
    G(0),  T(1),  A(2),  C(3);
    Nucleotidos(int adnCode) {}

}

