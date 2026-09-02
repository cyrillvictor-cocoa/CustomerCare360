package org.example.customercare360.Repository;

import org.example.customercare360.Entity.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplaintRepository
        extends JpaRepository<Complaint, Integer> {
}