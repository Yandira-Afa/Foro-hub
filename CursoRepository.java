package com.alura.foro.hub.dominio.curso.repository;

import com.alura.foro.hub.dominio.curso.Curso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
Page<Curso> findAllByActivoTrue(Pageable pageable);
}
