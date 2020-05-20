package aiu.edu.kg.FaceRecognation.service;

import aiu.edu.kg.FaceRecognation.entity.User;

import java.util.List;

public interface UserService {

    void save(User user);

    User findByUsername(String username);

    List<User> all();

    User findById(Long id);

    void delete(Long id);

    User update(User user, User userToUpdate);

    User findByIpAndToken(String ip, String token);
}
