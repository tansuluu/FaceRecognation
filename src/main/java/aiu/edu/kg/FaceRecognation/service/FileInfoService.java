package aiu.edu.kg.FaceRecognation.service;

import aiu.edu.kg.FaceRecognation.repository.FileInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileInfoService {

    @Autowired
    private FileInfoRepository fileInfoRepository;

}
