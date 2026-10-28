package com.example.resourcehub.services;

import com.example.resourcehub.models.Recurso;
import com.example.resourcehub.repository.RecursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecursoService {

    @Autowired
    RecursoRepository recursoRepository;

    public List<Recurso> obtenerTodos() {
        return recursoRepository.findAll();
    }

    public Optional<Recurso> obtenerPorId(Long id){
        return recursoRepository.findById(id);
    }

    public Recurso crearRecurso(Recurso recurso) {
        return recursoRepository.save(recurso);
    }

    // PUT -> actualizar el objeto completo
    // PATH -> actualizacion parcial


    public Optional<Recurso> actualizarCompleto(Long id, Recurso recursoActualizado) {
        return recursoRepository.findById(id).map(recursoExistente -> {
            // PUT actualiza el objeto completo
            recursoExistente.setTitulo(recursoActualizado.getTitulo());
            recursoExistente.setUrl(recursoActualizado.getUrl());
            recursoExistente.setTipo(recursoActualizado.getTipo());
            recursoExistente.setCategoria(recursoActualizado.getCategoria());
            return recursoRepository.save(recursoExistente);
        });
    }

    public Optional<Recurso> actuaizarParcial(Long id, Recurso recursoActualizado) {
        return recursoRepository.findById(id).map(recursoExistente -> {
            // PATCH Solo actualiza los campos que no vienen nulos
            if(recursoActualizado.getTitulo() != null)
                recursoExistente.setTitulo(recursoActualizado.getTitulo());
            if(recursoActualizado.getUrl() != null)
                recursoExistente.setUrl(recursoActualizado.getUrl());
            if(recursoActualizado.getTipo() != null)
                recursoExistente.setTipo(recursoActualizado.getTipo());
            if(recursoActualizado.getCategoria() != null)
                recursoExistente.setCategoria(recursoActualizado.getCategoria());
            return recursoRepository.save(recursoExistente);
        });
    }

    public boolean eliminarRecurso(Long id) {
        return recursoRepository.deleteById(id);
    }
}
