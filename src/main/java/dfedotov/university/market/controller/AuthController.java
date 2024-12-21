//package dfedotov.university.market.controller;
//
//import dfedotov.university.market.entity.User;
//import dfedotov.university.market.repository.UserRepository;
//import dfedotov.university.market.service.UserService;
//import lombok.AllArgsConstructor;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.List;
//
//@Controller
//@RequiredArgsConstructor
//public class AuthController {
//
//    private final UserRepository userRepository;
//    private final UserService userService;
//
//    @GetMapping("/login")
//    public String login() {
//        return "login";
//    }
//
//    @GetMapping("/register")
//    public String showRegistrationForm(Model model) {
//        model.addAttribute("user", new User());
//        return "register";
//    }
//
//    @PostMapping("/register")
//    public String registerUser(@RequestParam String username, @RequestParam String password, Model model) {
//        if (userRepository.existsByUsername(username)) {
//            model.addAttribute("user", new User());
//            model.addAttribute("error", "Username is already taken.");
//            return "register";
//        }
//
//        userService.save(username, password);
//
//        return "redirect:/login";
//    }
//}

