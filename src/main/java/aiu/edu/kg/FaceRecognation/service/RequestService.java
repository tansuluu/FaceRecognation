package aiu.edu.kg.FaceRecognation.service;

import aiu.edu.kg.FaceRecognation.dto.RequestDTO;
import aiu.edu.kg.FaceRecognation.entity.Request;
import aiu.edu.kg.FaceRecognation.entity.User;
import aiu.edu.kg.FaceRecognation.enums.ResultCode;
import aiu.edu.kg.FaceRecognation.enums.ResultDetail;
import aiu.edu.kg.FaceRecognation.enums.StageStatus;
import aiu.edu.kg.FaceRecognation.model.ResponseMessage;
import aiu.edu.kg.FaceRecognation.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RequestProcessService requestProcessService;

    public Optional<Request> findById(Long id){
        return requestRepository.findById(id);
    }

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

    public ResponseEntity<?> validateAndSave(RequestDTO requestDTO){
        ResponseMessage<Long> responseMessage = new ResponseMessage<>(ResultCode.FAIL);
        User user = userService.findByUsername(requestDTO.getUsername());
        HttpStatus status = HttpStatus.BAD_REQUEST;
        if (user==null){
            responseMessage.setDetailCode(ResultDetail.USER_NOT_FOUND);
        }
        else if (requestDTO.getFiles()==null || requestDTO.getFiles().size()==0){
            responseMessage.setDetailCode(ResultDetail.FILES_ARE_EMPTY);
        }
        else if(requestDTO.getFileType() == null){
            responseMessage.setDetailCode(ResultDetail.FILE_TYPE_IS_WRONG);
        }
        else{
            Request request = new Request(requestDTO.getTitle(), requestDTO.getFileType(), requestDTO.getPersonPosition(), requestDTO.getGender(), user);
            request = this.save(request);
            for(MultipartFile file : requestDTO.getFiles()) {
                requestProcessService.save(request, file);
            }
            responseMessage.setResult(request.getId());
            responseMessage.setDetailCode(ResultDetail.OK);
            responseMessage.setResultCode(ResultCode.SUCCESS);
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        }
        return new ResponseEntity<>(responseMessage, status);
    }
}
