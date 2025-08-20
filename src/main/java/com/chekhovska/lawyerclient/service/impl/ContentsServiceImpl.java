package com.chekhovska.lawyerclient.service.impl;

import com.chekhovska.lawyerclient.dto.ContentDto;
import com.chekhovska.lawyerclient.exception.NotFoundException;
import com.chekhovska.lawyerclient.model.Contents;
import com.chekhovska.lawyerclient.model.Images;
import com.chekhovska.lawyerclient.model.Type;
import com.chekhovska.lawyerclient.repository.ContentsRepository;
import com.chekhovska.lawyerclient.service.ContentsService;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.NoSuchElementException;

@Service
//@EnableCaching
@RequiredArgsConstructor
public class ContentsServiceImpl implements ContentsService {

    private final ContentsRepository contentsRepository;
    private ModelMapper modelMapper;

    @Override
    public ContentDto getContentsByType(String type) {
        Type contentsType = Type.valueOf(type);
        Contents contents = contentsRepository.findAllByType(contentsType).get();
//                .orElseThrow(() ->
//                        new NotFoundException("No content of this type found"));
        ContentDto contentDto = modelMapper.map(contents, ContentDto.class);
        return contentDto;
    }

    @Override
    public void createContent(ContentDto contentDto){
        contentsRepository.save(modelMapper.map(contentDto, Contents.class));
    }
}
