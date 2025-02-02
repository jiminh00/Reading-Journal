package com.jimin.readingjournal.domain.journal.utils;

import com.jimin.readingjournal.global.exception.custom.EmptyFileException;
import com.jimin.readingjournal.global.exception.custom.FileUploadFailException;
import com.jimin.readingjournal.global.exception.custom.InvalidFileNameException;
import com.jimin.readingjournal.global.exception.custom.InvalidFileTypeException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.UUID;

@Component
public class FileUtils {
    private static final Set<String> ALLOWED_MIME_TYPES = Set.of("image/jpeg", "image/png", "image/gif");

    private static final String STATIC_IMAGE_DIR = "static/book-images/";

    public String saveFile(MultipartFile multipartFile) {
        // 파일이 비어있는지 확인
        if (multipartFile.isEmpty()) {
            throw new EmptyFileException();
        }

        try {
            String originalFilename = multipartFile.getOriginalFilename();
            if (originalFilename == null || !originalFilename.contains(".")) {
                throw new InvalidFileNameException();
            }

            String fileExtension = getFileExtension(originalFilename);

            String mimeType = multipartFile.getContentType();
            if (mimeType == null || !ALLOWED_MIME_TYPES.contains(mimeType)) {
                throw new InvalidFileTypeException();
            }

            String uniqueFileName = UUID.randomUUID() + "." + fileExtension;

            // 동적으로 디렉토리 생성
            File directory = new File(new ClassPathResource("static").getFile(), "book-images");
            if (!directory.exists()) {
                directory.mkdirs();
            }

            File savedFile = new File(directory, uniqueFileName);
            multipartFile.transferTo(savedFile);

            return "/book-images/" + uniqueFileName;
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileUploadFailException();
        }
    }

    // 파일 확장자 추출
    private String getFileExtension(String filename) {
        return filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
    }
}