package aiu.edu.kg.FaceRecognation.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@Service
public class StorageService {

    @Value("${file.location.path}")
    private String path;

    Logger log = LoggerFactory.getLogger(this.getClass().getName());

    public void store(MultipartFile file, String name){
        Path rootLocation = Paths.get(path);
        try {
            Files.copy(file.getInputStream(), rootLocation.resolve(name), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            log.error("FAIL! in store ", e);
        }
    }

    public Resource loadFile(String filename) {
        Path rootLocation = Paths.get(path);
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()) {
                return resource;
            }else{
                log.error("FAIL! could not find file");
            }
        } catch (MalformedURLException e) {
            log.error("Error while loading file", e);
        }
        return null;
    }

    public void init() {
        try {
            Files.createDirectory(Paths.get(path));
        } catch (IOException e) {
            log.error("Could not initialize storage!", e);
        }
    }
}