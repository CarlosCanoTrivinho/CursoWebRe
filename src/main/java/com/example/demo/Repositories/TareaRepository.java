package com.example.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entities.Tarea;


public interface TareaRepository extends JpaRepository<Tarea, Long>{

}
