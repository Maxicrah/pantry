package com.maxi.pantrypos.service.imp;

import com.maxi.pantrypos.dao.IFileDAO;
import com.maxi.pantrypos.model.File;
import com.maxi.pantrypos.response.ResponseFile;
import com.maxi.pantrypos.service.IFileService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FileService implements IFileService {

    private final IFileDAO fileDAO;
    public FileService(IFileDAO fileDAO) {
        this.fileDAO = fileDAO;
    }


    @Override
    public File store(MultipartFile file) throws IOException {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        File newFile = File.builder()
                .name(fileName)
                .type(file.getContentType())
                .data(file.getBytes())
                .build();
        return this.fileDAO.save(newFile);

    }

    @Override
    public List<ResponseFile> getAllFiles() {
        List<ResponseFile> files = this.fileDAO.findAll().stream().map(doFile ->{
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("fileManager/files/")
                    .path(doFile.getId().toString())
                    .toUriString();
            return ResponseFile.builder()
                    .name(doFile.getName())
                    .url(fileDownloadUri)
                    .type(doFile.getType())
                    .size(doFile.getData().length).build();
        }).collect(Collectors.toList());
        return files;
    }

    @Override
    public Optional<File> getFile(UUID id) throws FileNotFoundException {
        Optional<File> file = this.fileDAO.findById(id);
        if(file.isPresent()) {
            return file;
        }
        throw new FileNotFoundException();
    }
}
