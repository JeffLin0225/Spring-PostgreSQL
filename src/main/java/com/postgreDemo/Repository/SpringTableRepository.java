package com.postgreDemo.Repository;

import com.postgreDemo.Entity.SpringTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringTableRepository extends JpaRepository<SpringTable, Integer> {
}
