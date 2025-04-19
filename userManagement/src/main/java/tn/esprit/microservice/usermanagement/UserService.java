package tn.esprit.microservice.usermanagement;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> afficherTous() {
        return userRepository.findAll();
    }

    public User afficherParId(int id) {
        return userRepository.findById(id).orElse(null);
    }
    /*Ajout */
    public User addUser(User user) {
        return userRepository.save(user);
    }
    public User updateUser(int id, User newUser) {
        if (userRepository.findById(id).isPresent()) {
            User existingUser= userRepository.findById(id).get();
            existingUser.setEmail(newUser.getEmail());
            existingUser.setPassword(newUser.getPassword());
            existingUser.setFirstName(newUser.getFirstName());
            existingUser.setLastName(newUser.getLastName());
            return userRepository.save(existingUser);
        } else
            return null;
    }
    public String deleteUser(int id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
            return "user supprimé";
        } else
            return "user non supprimé";
    }
} 