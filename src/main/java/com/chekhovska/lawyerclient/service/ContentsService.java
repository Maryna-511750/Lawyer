package com.chekhovska.lawyerclient.service;

import com.chekhovska.lawyerclient.dto.ContentDto;

public interface ContentsService {
    ContentDto getContentsByType(String type);

    void createContent(ContentDto contentDto);
}
