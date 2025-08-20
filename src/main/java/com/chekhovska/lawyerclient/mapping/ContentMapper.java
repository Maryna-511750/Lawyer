package com.chekhovska.lawyerclient.mapping;

import com.chekhovska.lawyerclient.dto.ContentDto;
import com.chekhovska.lawyerclient.model.Contents;
import com.chekhovska.lawyerclient.model.Type;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class ContentMapper  extends AbstractConverter<ContentDto, Contents> {
    @Override
    protected Contents convert(ContentDto contentDto) {
        return Contents.builder()
                .title(contentDto.getTitle())
                .description(contentDto.getDescription())
                .image(contentDto.getImage())
                .type(Type.valueOf(contentDto.getType()))
                .build();
    }
}
