package com.fabrakadabra.webapp.repository;

import com.fabrakadabra.webapp.model.PlayGround;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PlayGroundRepository extends JpaRepository<PlayGround,Long> {
}
