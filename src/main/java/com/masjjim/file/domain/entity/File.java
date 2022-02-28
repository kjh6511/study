package com.masjjim.file.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class File {
    private Integer fileNo;
    private String filePath;
    private String fileType;
    private String fileName;
    private String fileTrName;
    private String fileCode;
    private String fileRel;
    private String memAuth;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fileRegDt;
}
