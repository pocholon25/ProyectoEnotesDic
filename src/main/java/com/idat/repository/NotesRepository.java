package com.idat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.idat.entity.Notes;

@Repository
public interface NotesRepository extends JpaRepository<Notes, Integer> {

}
