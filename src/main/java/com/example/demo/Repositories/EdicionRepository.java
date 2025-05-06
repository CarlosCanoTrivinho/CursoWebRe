package com.example.demo.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Entities.Edicion;

public interface EdicionRepository extends JpaRepository<Edicion, Long> {

	List<Edicion> findAbandonmentRate();
}
