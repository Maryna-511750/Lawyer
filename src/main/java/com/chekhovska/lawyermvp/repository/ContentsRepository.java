package com.chekhovska.lawyermvp.repository;

import com.chekhovska.lawyermvp.model.Contents;
import com.chekhovska.lawyermvp.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentsRepository extends JpaRepository<Contents, Long> {
    List<Contents> findAllBySectionOrderById(Section section);
}
