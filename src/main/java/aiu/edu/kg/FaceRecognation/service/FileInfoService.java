package aiu.edu.kg.FaceRecognation.service;

import aiu.edu.kg.FaceRecognation.entity.RequestProcess;
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
            RequestProcess requestProcess = new RequestProcess(request);
            requestProcess = this.fileInfoRepository.saveAndFlush(requestProcess);
            String fileName = FILE+ requestProcess.getId()+ "." + FilenameUtils.getExtension(file.getOriginalFilename());
            storageService.store(file, fileName);
            requestProcess.setFileName(fileName);
            this.fileInfoRepository.save(requestProcess);
        }catch (Exception e){
            log.error("Exception in save file info, request ="+request.getId() + "\n"+ e);
        }
    }

    public List<RequestProcess> findAllByRequest(Request request){
        return fileInfoRepository.findAllByRequest(request);
    }


}
