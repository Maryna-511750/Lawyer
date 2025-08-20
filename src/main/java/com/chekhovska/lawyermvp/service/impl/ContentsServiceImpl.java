package com.chekhovska.lawyermvp.service.impl;

import com.chekhovska.lawyermvp.exception.NullEntityReferenceException;
import com.chekhovska.lawyermvp.model.Contents;
import com.chekhovska.lawyermvp.repository.ContentsRepository;
import com.chekhovska.lawyermvp.repository.SectionRepository;
import com.chekhovska.lawyermvp.service.ContentsService;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentsServiceImpl implements ContentsService {

    private static final Logger logger = LoggerFactory.getLogger(ContentsServiceImpl.class);


    private final ContentsRepository contentsRepository;
    private final SectionRepository sectionRepository;

    public ContentsServiceImpl(ContentsRepository contentsRepository, SectionRepository sectionRepository) {
        this.contentsRepository = contentsRepository;
        this.sectionRepository = sectionRepository;
    }

    @Override
    public Contents create(Contents contents) {
        if (contents != null) {
            return contentsRepository.save(contents);
        }
        throw new NullEntityReferenceException("Contents cannot be 'null'");
    }

    @Override
    public Contents readById(Long id) {
        return contentsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contents with id " + id + " not found"));
    }

    @Override
    public Contents update(Contents content) {
        if (content != null) {
            readById(content.getId());
            return contentsRepository.save(content);
        }
        throw new NullEntityReferenceException("Contents cannot be 'null'");
    }

    @Override
    public void delete(Long id) {
        Contents task = readById(id);
        contentsRepository.delete(task);
    }

    @Override
    public List<Contents> getAll() {
        return contentsRepository.findAll();
    }

    @Override
    public List<Contents> getAllBySectionType(String type) {
        return contentsRepository.findAllBySectionOrderById(sectionRepository.findByType(type).orElseThrow(
                () -> new EntityNotFoundException("Section with name " + type + " not found")));//Type.valueOf(type));
    }
}

