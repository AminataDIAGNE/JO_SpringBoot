package com.jo.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jo.app.entity.Epreuve;

import java.time.LocalDateTime;
import java.util.List;

public interface EpreuveRepository extends JpaRepository<Epreuve, Long>{

    List<Epreuve> findAllByDateAfter(LocalDateTime localDateTime);
}
