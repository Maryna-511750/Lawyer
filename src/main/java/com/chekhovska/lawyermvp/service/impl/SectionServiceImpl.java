package com.chekhovska.lawyermvp.service.impl;

import com.chekhovska.lawyermvp.exception.NullEntityReferenceException;
import com.chekhovska.lawyermvp.model.Section;
import com.chekhovska.lawyermvp.repository.SectionRepository;
import com.chekhovska.lawyermvp.service.SectionService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionServiceImpl implements SectionService {
    private final SectionRepository sectionRepository;

    public SectionServiceImpl(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    @Override
    public Section create(Section section) {
        if (section != null) {
            return sectionRepository.save(section);
        }
        throw new NullEntityReferenceException("Section cannot be 'null'");
    }

    @Override
    public Section readById(Long id) {
        return sectionRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Section with id " + id + " not found"));
    }

    @Override
    public Section update(Section section) {
        if (section != null) {
            readById(section.getId());
            return sectionRepository.save(section);
        }
        throw new NullEntityReferenceException("Section cannot be 'null'");
    }

    @Override
    public void delete(Long id) {
        Section section = readById(id);
        sectionRepository.delete(section);
    }

    @Override
    public List<Section> getAll() {
        return sectionRepository.findAll();
    }

    @Override
    public Section findByType(String type){
        return sectionRepository.findByType(type).orElseThrow(
                () -> new EntityNotFoundException("Section with type " + type + " not found"));
    }

    @Override
    public List<Section> findAllByVisible(){
        return sectionRepository.findAllByVisibleIsTrue();
    }
}
