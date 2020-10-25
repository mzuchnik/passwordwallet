package pl.mzuchnik.mypasswordwallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.encrypt.AesBytesEncryptor;
import org.springframework.security.crypto.encrypt.BouncyCastleAesCbcBytesEncryptor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.mzuchnik.mypasswordwallet.encoder.AesEncryptor;
import pl.mzuchnik.mypasswordwallet.entity.Password;
import pl.mzuchnik.mypasswordwallet.entity.User;
import pl.mzuchnik.mypasswordwallet.service.PasswordService;
import pl.mzuchnik.mypasswordwallet.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/wallet")
public class WalletController {

    private UserService userService;
    private PasswordService passwordService;

    @Autowired
    public WalletController(UserService userService, PasswordService passwordService) {
        this.userService = userService;
        this.passwordService = passwordService;
    }

    @GetMapping
    public String showUserWallet(Model model,
                                 Principal principal,
                                 @RequestParam(required = false) String encryptor)
    {
        User user = userService.findByLogin(principal.getName());

        if (encryptor != null) {
            if(encryptor.equals("decrypt")) {
                user.getUserPasswords().forEach(
                        password -> {
                            password.setWebPassword(
                                    new AesEncryptor().decrypt(
                                            password.getWebPassword(), user.getPassword()));
                        });
            }

        } else {
            user.getUserPasswords().forEach(p -> {
                p.setWebPassword("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
            });
        }


        model.addAttribute("userPasswords", user.getUserPasswords());
        model.addAttribute("passwordForm", new Password());

        return "my-wallet";
    }

    @PostMapping("/add")
    public String showFormToAddPassword(@ModelAttribute Password password, Principal principal) {
        passwordService.saveForUser(password, userService.findByLogin(principal.getName()));
        return "redirect:/wallet";
    }

}
