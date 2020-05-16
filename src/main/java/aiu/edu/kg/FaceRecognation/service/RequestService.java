package aiu.edu.kg.FaceRecognation.service;

import aiu.edu.kg.FaceRecognation.dto.RequestDTO;
import aiu.edu.kg.FaceRecognation.entity.Request;
import aiu.edu.kg.FaceRecognation.entity.User;
import aiu.edu.kg.FaceRecognation.enums.RequestStatus;
import aiu.edu.kg.FaceRecognation.enums.ResultCode;
import aiu.edu.kg.FaceRecognation.enums.ResultDetail;
import aiu.edu.kg.FaceRecognation.enums.StageStatus;
import aiu.edu.kg.FaceRecognation.model.ResponseMessage;
import aiu.edu.kg.FaceRecognation.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RequestProcessService requestProcessService;

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

    public ResponseMessage<Long> validateAndSave(RequestDTO requestDTO){
        ResponseMessage<Long> responseMessage = new ResponseMessage<>(ResultCode.FAIL);
        User user = userService.findByUsername(requestDTO.getUsername());
        if (user==null){
            responseMessage.setDetailCode(ResultDetail.USER_NOT_FOUND);
            return responseMessage;
        }
        else if (requestDTO.getFiles().size()==0){
            responseMessage.setDetailCode(ResultDetail.FILES_ARE_EMPTY);
            return responseMessage;
        }
        else if(requestDTO.getFileType() == null){
            responseMessage.setDetailCode(ResultDetail.FILE_TYPE_IS_WONG);
            return responseMessage;
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
            return responseMessage;
        }
    }
}
