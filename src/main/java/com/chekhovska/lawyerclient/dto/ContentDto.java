package com.chekhovska.lawyerclient.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContentDto {
    private String title;
    private String description;
    private String image;
    private String type;
}
