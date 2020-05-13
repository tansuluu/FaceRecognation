package aiu.edu.kg.FaceRecognation.service;

import aiu.edu.kg.FaceRecognation.entity.FileInfo;
import aiu.edu.kg.FaceRecognation.entity.Request;
import aiu.edu.kg.FaceRecognation.repository.FileInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
public class FileInfoService {

    private final String FILE = "file_";

    @Autowired
    private StorageService storageService;

    @Autowired
    private FileInfoRepository fileInfoRepository;

    public void save(Request request, MultipartFile file){
        try {
            FileInfo fileInfo = new FileInfo(request, String.valueOf(file.getSize()),file.getContentType());
            fileInfo = this.fileInfoRepository.save(fileInfo);
            file.getOriginalFilename().replace(file.getOriginalFilename(), FILE+fileInfo.getId()+ "." + FilenameUtils.getExtension(file.getOriginalFilename())).toLowerCase();
            storageService.store(file);
            fileInfo.setFileName(file.getOriginalFilename());
            this.fileInfoRepository.save(fileInfo);
        }catch (Exception e){
            log.error("Exception in save file info, request ="+request.getId() + "\n"+ e);
        }

    }

}
