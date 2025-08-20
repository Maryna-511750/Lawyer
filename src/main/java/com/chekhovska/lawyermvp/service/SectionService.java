package com.chekhovska.lawyermvp.service;


import com.chekhovska.lawyermvp.model.Section;

import java.util.List;

public interface SectionService {
    Section create(Section section);
    Section readById(Long id);
    Section update(Section section);
    void delete(Long id);
    List<Section> getAll();
    Section findByType(String type);

    List<Section> findAllByVisible();
}
