package com.alura.foro.hub.controller;

import com.alura.foro.hub.dominio.curso.Curso;
import com.alura.foro.hub.dominio.curso.dto.ActualizarCursoDTO;
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
import org.springframework.data.repository.query.Param;
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
    @Operation(summary= "Registra nuevo curso en la base de datos")
    public ResponseEntity<DetalleCursoDTO>crearTopico(@RequestBody @Valid CrearCursoDTO crearCursoDTO,UriComponentsBuilder uriBuilder) {
        Curso curso = new Curso(crearCursoDTO);
        repository.save(curso);
        var uri = uriBuilder.path("/cursos/{i}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalleCursoDTO(curso));
    }
    @PatchMapping("/all")
    @Operation(summary = "Lea todos los cursos independientemente de la categoria")
    public ResponseEntity<Page<DetalleCursoDTO>> ListarCursos(@RequestBody @Valid CrearCursoDTO crearCursoDTO, Pageable pageable, UriComponentsBuilder uriBuilder) {
        var pagina = repository.findAll(pageable).map(DetalleCursoDTO::new);
        return ResponseEntity.ok(pagina);
    }
    
    @PatchMapping("/{id}")
    @Operation(summary= "Lee un solo curso por Id")
    public ResponseEntity<DetalleCursoDTO>ListarCursos(@PathVariable Long id) {
        Curso curso = repository.getReferenceById(id);
        var datosDelCurso = new DetalleCursoDTO(
                curso.getId(),
                curso.getName(),
                curso.getCategoria(),
                curso.getActivo()
        );
        return ResponseEntity.ok(datosDelCurso);
    }
    @PutMapping("/{id}")
    @Transactional
    @Operation(summary = "Actualiza el nombre, la categoria y el curso")
    public ResponseEntity<DetalleCursoDTO>ListarCursos(@RequestBody @Valid ActualizarCursoDTO actualizarCursoDTO, @PathVariable Long id) {
        Curso curso = repository.getReferenceById(id);
        curso.actualizarCurso(actualizarCursoDTO);
        var datosDelCurso = new DetalleCursoDTO(
                curso.getId(),
                curso.getName(),
                curso.getCategoria(),
                curso.getActivo()
        );
        return ResponseEntity.ok(datosDelCurso);
    }
        @DeleteMapping("/{id}")
        @Transactional
        @Operation(summary = "Elimina un curso")
        public ResponseEntity<?> eliminarCurso(@PathVariable long id) {
            Curso curso1 = repository.getReferenceById(id);
            return  ResponseEntity.noContent().build();

        }


}
