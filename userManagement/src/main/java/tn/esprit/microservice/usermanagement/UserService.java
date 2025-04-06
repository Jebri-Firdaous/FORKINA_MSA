package tn.esprit.microservice.usermanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private EmailService emailService;

    public List<User> afficherTous() {
        return userRepository.findAll();
    }

    public User afficherParId(int id) {
        return userRepository.findById(id).orElse(null);
    }
    
    /*Ajout */
    public User addUser(User user) {
        logger.info("Début création d'utilisateur: {}", user.getEmail());
        
        // Vérifier si l'utilisateur existe déjà
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            logger.warn("Un utilisateur avec l'email {} existe déjà", user.getEmail());
            throw new RuntimeException("Un utilisateur avec cet email existe déjà");
        }
        
        // Générer un token de vérification
        String token = UUID.randomUUID().toString();
        user.setVerificationToken(token);
        user.setVerified(false);
        
        // Sauvegarder l'utilisateur
        User savedUser = userRepository.save(user);
        logger.info("Utilisateur sauvegardé avec succès: {}", savedUser.getIdUser());
        
        // Envoyer l'email de vérification
        boolean emailSent = emailService.sendVerificationEmail(savedUser, token);
        if (emailSent) {
            logger.info("Email de vérification envoyé à: {}", user.getEmail());
        } else {
            logger.warn("Échec de l'envoi de l'email à: {}", user.getEmail());
        }
        
        return savedUser;
    }
    
    // Méthode login qui vérifie l'email et le mot de passe et aussi si l'utilisateur a vérifié son compte
    public Map<String, Object> login(String email, String password) {
        logger.info("Tentative de connexion pour: {}", email);
        Map<String, Object> response = new HashMap<>();
        
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) {
            logger.warn("Email non trouvé: {}", email);
            response.put("success", false);
            response.put("message", "Email ou mot de passe incorrect");
            return response;
        }
        
        User user = userOpt.get();
        
        // Vérifier le mot de passe
        if (!user.getPassword().equals(password)) {
            logger.warn("Mot de passe incorrect pour: {}", email);
            response.put("success", false);
            response.put("message", "Email ou mot de passe incorrect");
            return response;
        }
        
        // Vérifier si l'utilisateur a vérifié son compte
        if (!user.isVerified()) {
            logger.warn("Utilisateur non vérifié: {}", email);
            response.put("success", false);
            response.put("message", "Veuillez vérifier votre email avant de vous connecter");
            return response;
        }
        
        // L'utilisateur est authentifié et vérifié
        logger.info("Connexion réussie pour: {}", email);
        response.put("success", true);
        response.put("message", "Connecté avec succès");
        response.put("user", user);
        
        return response;
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
    
    // Méthode pour vérifier l'email avec le token
    public String verifyEmail(String token) {
        logger.info("Tentative de vérification avec token: {}", token);
        Optional<User> user = userRepository.findByVerificationToken(token);
        if (user.isPresent()) {
            User verifiedUser = user.get();
            verifiedUser.setVerified(true);
            verifiedUser.setVerificationToken(null); // Token utilisé une seule fois
            userRepository.save(verifiedUser);
            logger.info("Utilisateur {} vérifié avec succès", verifiedUser.getEmail());
            return "Votre email a été vérifié avec succès!";
        }
        logger.warn("Token de vérification invalide: {}", token);
        return "Token de vérification invalide!";
    }
    
    // Méthode de recherche avancée qui détecte le type de recherche
    public List<User> findBySearchTerm(String searchTerm) {
        // Vérifier si le terme de recherche ressemble à un email
        if (isValidEmail(searchTerm)) {
            return userRepository.findByEmailContaining(searchTerm);
        } 
        // Sinon, chercher dans firstName et lastName
        else {
            List<User> resultsByFirstName = userRepository.findByFirstNameContaining(searchTerm);
            List<User> resultsByLastName = userRepository.findByLastNameContaining(searchTerm);
            
            // Combiner les résultats (sans doublons)
            for (User user : resultsByLastName) {
                if (!resultsByFirstName.contains(user)) {
                    resultsByFirstName.add(user);
                }
            }
            return resultsByFirstName;
        }
    }
    
    // Méthode pour vérifier si une chaîne est un email
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }
} 