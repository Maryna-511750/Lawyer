package com.chekhovska.lawyermvp.service;

import com.chekhovska.lawyermvp.model.Contents;

import java.util.List;

public interface ContentsService {
    Contents create(Contents task);
    Contents readById(Long id);
    Contents update(Contents task);
    void delete(Long id);
    List<Contents> getAll();
    List<Contents> getAllBySectionType(String type);
}
