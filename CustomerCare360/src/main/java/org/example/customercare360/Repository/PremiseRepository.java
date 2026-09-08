package org.example.customercare360.Repository;

import org.example.customercare360.Entity.Premise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PremiseRepository extends JpaRepository<Premise, Integer> {
}