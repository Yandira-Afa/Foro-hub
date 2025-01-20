package com.alura.foro.hub.dominio.curso.dto;

import com.alura.foro.hub.dominio.curso.Categoria;
import com.alura.foro.hub.dominio.curso.Curso;

public record DetalleCursoDTO(
    Long id,
    String name,
    Categoria categoria,
    Boolean activo) {

    public DetalleCursoDTO(Curso curso) {
        this(
                curso.getId(),
                curso.getName(),
                curso.getCategoria(),
                curso.getActivo());

    }

}
