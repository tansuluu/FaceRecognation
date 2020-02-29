package aiu.edu.kg.FaceRecognation.service;

import aiu.edu.kg.FaceRecognation.entity.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
