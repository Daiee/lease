package com.daie.lease.controller;

import com.daie.lease.common.result.Result;
import com.daie.lease.service.FileService;
import io.minio.errors.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("file")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("upload")
    public Result<String> upload(@RequestParam MultipartFile file) throws ServerException, InsufficientDataException,
        ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException,
        XmlParserException, InternalException {

        String url = fileService.upload(file);
        return Result.success(url);
    }
}
