package com.jimin.readingjournal.domain.journal.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookImageDto {
    private Long bookImageId;
    private String bookImageUrl;
    private Long bookId;
    private String userId;
    private String bookImageName;
}
