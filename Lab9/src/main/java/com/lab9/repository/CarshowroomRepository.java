package com.lab9.repository;

import com.lab9.model.Carshowroom;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarshowroomRepository extends JpaRepository<Carshowroom, Long> {
}
