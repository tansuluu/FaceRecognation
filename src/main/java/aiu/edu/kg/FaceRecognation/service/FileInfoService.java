package aiu.edu.kg.FaceRecognation.service;

import aiu.edu.kg.FaceRecognation.entity.FileInfo;
import aiu.edu.kg.FaceRecognation.entity.Request;
import aiu.edu.kg.FaceRecognation.repository.FileInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
            fileInfo = this.fileInfoRepository.saveAndFlush(fileInfo);
            String fileName = FILE+fileInfo.getId()+ "." + FilenameUtils.getExtension(file.getOriginalFilename());
            storageService.store(file, fileName);
            fileInfo.setFileName(fileName);
            this.fileInfoRepository.save(fileInfo);
        }catch (Exception e){
            log.error("Exception in save file info, request ="+request.getId() + "\n"+ e);
        }
    }

    public List<FileInfo> findAllByRequest(Request request){
        return fileInfoRepository.findAllByRequest(request);
    }


}
