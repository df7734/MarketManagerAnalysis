//package dfedotov.university.market.service;
//
//import dfedotov.university.market.entity.User;
//import dfedotov.university.market.repository.UserRepository;
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class UserServiceImpl implements UserService {
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    @Override
//    @Transactional
//    public void save(String username, String password) {
//        User user = new User();
//        user.setUsername(username);
//        user.setPassword(passwordEncoder.encode(password));
//        user.setRoles(List.of("USER"));
//        userRepository.save(user);
//    }
//}
