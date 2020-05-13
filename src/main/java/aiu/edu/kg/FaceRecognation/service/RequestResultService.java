package aiu.edu.kg.FaceRecognation.service;

import aiu.edu.kg.FaceRecognation.entity.Request;
import aiu.edu.kg.FaceRecognation.entity.RequestResult;
import aiu.edu.kg.FaceRecognation.repository.RequestResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestResultService {

    @Autowired
    private RequestResultRepository requestResultRepository;

}
