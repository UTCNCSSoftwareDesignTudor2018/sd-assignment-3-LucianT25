package com.utcn.Articles.Persistence.Repository;

import com.utcn.Articles.Persistence.Entity.Writer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WriterRepository extends JpaRepository<Writer, Long>{
    public Writer findByUsername(String username);
}
