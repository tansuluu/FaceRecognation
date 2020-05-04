package aiu.edu.kg.FaceRecognation.service;

public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);
}