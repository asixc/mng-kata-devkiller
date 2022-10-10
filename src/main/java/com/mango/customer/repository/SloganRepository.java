package com.mango.customer.repository;

import com.mango.customer.models.Slogan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SloganRepository extends JpaRepository<Slogan, Long> {
}
