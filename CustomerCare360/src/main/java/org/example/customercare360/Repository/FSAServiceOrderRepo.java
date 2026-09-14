package org.example.customercare360.Repository;

import org.example.customercare360.Entity.FSAServiceOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FSAServiceOrderRepo
        extends JpaRepository<FSAServiceOrder, Integer> {

    Optional<List<FSAServiceOrder>> findByAgentId(Integer agentId);

}