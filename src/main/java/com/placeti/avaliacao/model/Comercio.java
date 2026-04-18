package com.placeti.avaliacao.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="Comercio")
public class Comercio {
    @Column(name="id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name="nome_comercio", nullable = false)
    private String nomeComercio;

    @Column(name ="responsavel_comercio")
    private String responsavelComercio;

    @Column(name = "tipo_comercio", nullable = false)
    private String tipoComercio;
}
