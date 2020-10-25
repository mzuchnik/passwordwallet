package pl.mzuchnik.mypasswordwallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.mzuchnik.mypasswordwallet.encoder.AesEncryptor;
import pl.mzuchnik.mypasswordwallet.entity.Password;
import pl.mzuchnik.mypasswordwallet.entity.User;
import pl.mzuchnik.mypasswordwallet.service.UserService;

import java.awt.print.PrinterIOException;
import java.security.Principal;

@Controller
public class MainController {

    private UserService userService;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showMainPage() {
        return "redirect:/wallet";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login-page";
    }

    @GetMapping("/sign-up")
    public String showSignUpPage(Model model) {
        model.addAttribute("user", new User());
        return "registration-page";
    }

    @PostMapping("/sign-up")
    public String processSignUpForm(@ModelAttribute User user, @RequestParam(name = "encrypt-type") String encryptType) {
        userService.saveUserWithPasswordEncoder(user, encryptType);
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        user.getLogin(),
                        user.getPassword()
                )
        );
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String processLogout(){
        SecurityContextHolder.getContext().setAuthentication(null);

        return "redirect:/login";
    }

    @GetMapping("/changePassword")
    public String showChangePasswordView(){
        return "change-password";
    }

    @PostMapping("/changePassword")
    public String processChangePassword(Principal principal,
                                        @RequestParam(name = "password") String password,
                                        @RequestParam(name = "encrypt") String encrypt){
        User user = userService.findByLogin(principal.getName());

        for (Password userPassword : user.getUserPasswords()) {
            userPassword.setWebPassword(new AesEncryptor().decrypt(userPassword.getWebPassword(),user.getPassword()));
        }

        user.setPassword(password);
        userService.saveUserWithPasswordEncoder(user, encrypt);
        return "redirect:/logout";
    }

}
