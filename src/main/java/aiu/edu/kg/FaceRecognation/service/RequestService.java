package aiu.edu.kg.FaceRecognation.service;

import aiu.edu.kg.FaceRecognation.entity.Request;
import aiu.edu.kg.FaceRecognation.entity.User;
import aiu.edu.kg.FaceRecognation.enums.RequestStatus;
import aiu.edu.kg.FaceRecognation.enums.StageStatus;
import aiu.edu.kg.FaceRecognation.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    public List<Request> all(){
        return requestRepository.findAll();
    }

    public Request save(Request request){
        request.setStatus(StageStatus.NEW);
        request.setCreatedDate(new Date());
        return requestRepository.save(request);
    }

    public Request justSave(Request request){
        return requestRepository.save(request);
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

    public void deleteByUser(User user){
        requestRepository.deleteAll(requestRepository.getAllByUser(user));
    }

    public List<Request> findAllByStatusAndSent(StageStatus status, int sent){
        return requestRepository.findAllByStatusAndSent(status, sent);
    }
}
