package aiu.edu.kg.FaceRecognation.service;

import aiu.edu.kg.FaceRecognation.entity.Request;
import aiu.edu.kg.FaceRecognation.entity.User;
import aiu.edu.kg.FaceRecognation.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    public List<Request> all(){
        return requestRepository.findAll();
    }

    public void save(Request request){
        requestRepository.save(request);
    }

    public Request getById(Long id){
        return requestRepository.getOne(id);
    }

    public List<Request> getAllByUser(User user){
        return requestRepository.getAllByUser(user);
    }

    public void delete(Long id){
        requestRepository.deleteById(id);
    }
}
