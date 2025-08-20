package com.chekhovska.lawyerclient.repository;

import com.chekhovska.lawyerclient.model.Contents;
import com.chekhovska.lawyerclient.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContentsRepository extends JpaRepository<Contents, Long>, JpaSpecificationExecutor<Contents> {
    Optional<Contents> findAllByType(Type type);
}
