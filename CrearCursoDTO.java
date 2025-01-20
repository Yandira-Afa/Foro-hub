package com.alura.foro.hub.dominio.curso.dto;

import com.alura.foro.hub.dominio.curso.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CrearCursoDTO(
        @NotBlank String name,
        @NotNull Categoria categoria) {
}
