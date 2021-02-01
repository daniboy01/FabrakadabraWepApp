package com.fabrakadabra.webapp.repository;

import com.fabrakadabra.webapp.model.Dimensions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DimensionsRepository extends JpaRepository<Dimensions,Long> {
}
