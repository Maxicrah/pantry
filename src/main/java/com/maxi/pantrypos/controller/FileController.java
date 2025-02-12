package com.maxi.pantrypos.controller;

import com.maxi.pantrypos.model.File;
import com.maxi.pantrypos.response.ResponseFile;
import com.maxi.pantrypos.response.ResponseMessage;
import com.maxi.pantrypos.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/fileManager")
public class FileController {
    private final IFileService fileService;
    public FileController(IFileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
            this.fileService.store(file);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("file uploaded successfully"));
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable UUID id) throws FileNotFoundException {
        File file = this.fileService.getFile(id).get();
        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_TYPE, file.getType())
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName()+"\"")
                .body(file.getData());
    }

    @GetMapping("/files")
    public ResponseEntity<List<ResponseFile>> getListFiles(){
        List<ResponseFile> files = this.fileService.getAllFiles();
        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

}
