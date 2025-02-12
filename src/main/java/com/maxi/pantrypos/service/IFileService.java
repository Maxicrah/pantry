package com.maxi.pantrypos.service;

import com.maxi.pantrypos.model.File;
import com.maxi.pantrypos.response.ResponseFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IFileService {

    // almacena carga archivos ala bd
    File store(MultipartFile file) throws IOException;
    //consulta lista archivos
    List<ResponseFile> getAllFiles();
    //permite descargar artchivos de la bd
    Optional<File> getFile(UUID id) throws FileNotFoundException;
}
