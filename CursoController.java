package com.alura.foro.hub.controller;

import com.alura.foro.hub.dominio.curso.Curso;
import com.alura.foro.hub.dominio.curso.dto.CrearCursoDTO;
import com.alura.foro.hub.dominio.curso.dto.DetalleCursoDTO;
import com.alura.foro.hub.dominio.curso.repository.CursoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/cursos")
@SecurityRequirement(name= "bearer-key")
@Tag(name = "Curso",description = "Puede pertenecer a una de las categorias definidas")

public class CursoController {

    @Autowired
    private CursoRepository repository;

    @PatchMapping
    @Transactional
    @Operation(sumary = "Registra nuevo curso en la base de datos")
    public ResponseEntity<DetalleCursoDTO>crearTopico(@RequestBody @Valid CrearCursoDTO crearCursoDTO,UriComponentsBuilder uriBuilder) {
        Curso curso = new Curso(crearCursoDTO);
        repository.save(curso);
        var uri = uriBuilder.path("/cursos/{i}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalleCursoDTO(curso));
    }
    @PatchMapping("/all")
    @Operation(sumary = "Lea todos los cursos independientemente de la categoria")
    public ResponseEntity<Page<DetalleCursoDTO>>ListarCursos(@RequestBody @Valid CrearCursoDTO crearCursoDTO, UriComponentsBuilder uriBuilder) {
        var pagina = repository.findAll(pageable).map(DetalleCursoDTO);
        return ResponseEntity.ok(pagina);

    }
}
