package com.alura.foro.hub.dominio.curso.dto;

import com.alura.foro.hub.dominio.curso.Categoria;

public record ActualizarCursoDTO(
    String name,
    Categoria categoria,
    Boolean activo) {

}
