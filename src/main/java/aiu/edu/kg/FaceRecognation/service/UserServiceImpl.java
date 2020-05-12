package aiu.edu.kg.FaceRecognation.service;

import aiu.edu.kg.FaceRecognation.entity.User;
import aiu.edu.kg.FaceRecognation.repository.RoleRepository;
import aiu.edu.kg.FaceRecognation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private RequestService requestService;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(Arrays.asList(roleRepository.getByName("USER"))));
        user.setCreatedDate(new Date());
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> all(){
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id){
        return userRepository.getOne(id);
    }

    @Override
    public void delete(Long id){
        requestService.deleteByUser(userRepository.getOne(id));
        userRepository.deleteById(id);
    }

    @Override
    public User update(User user, User userToUpdate){
        userToUpdate.setPassword(user.getPassword());
        userToUpdate.setFullName(user.getFullName());
        userToUpdate.setPosition(user.getPosition());
        this.save(userToUpdate);
        return userToUpdate;
    }
}
