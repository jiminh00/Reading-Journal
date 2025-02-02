package com.jimin.readingjournal.domain.journal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookImageDto {
    private Long bookImageId;
    private String bookImageUrl;
    private Long bookId;
    private String userId;
    private String bookImageName;
}
