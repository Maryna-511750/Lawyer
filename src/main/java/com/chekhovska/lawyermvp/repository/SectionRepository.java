package com.chekhovska.lawyermvp.repository;

import com.chekhovska.lawyermvp.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SectionRepository extends JpaRepository<Section, Long> {
    Optional<Section> findByType(String type);

    List<Section> findAllByVisibleIsTrue();
}
