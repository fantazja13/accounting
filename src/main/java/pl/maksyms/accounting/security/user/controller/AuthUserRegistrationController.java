package pl.maksyms.accounting.security.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.maksyms.accounting.security.user.AuthUserDTO;
import pl.maksyms.accounting.security.user.service.AuthUserService;

import javax.validation.Valid;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthUserRegistrationController {

    private final AuthUserService userService;

    @PostMapping(path = "auth/singup")
    public ResponseEntity<?> userRegistration(@Valid @RequestBody final AuthUserDTO newUser) {
        Map<String, String> messages = new HashMap<>();
        messages.put("timestamp", ZonedDateTime.now(ZoneOffset.UTC).toString());
        if (userService.isUsernameTaken(newUser.getUsername())) {
            messages.put("message", "Email is already taken.") ;
            return new ResponseEntity<>(messages, HttpStatus.CONFLICT);
        } else if (userService.isPasswordValid(newUser.getPassword()) && userService.passwordMatchesConfirm(newUser)) {
            userService.save(userService.prepareNewUserFromDTO(newUser));
            messages.put("message", "Verification code sent. Please confirm your email");
            return new ResponseEntity<>(messages, HttpStatus.OK);
        } else {
            messages.put("message", "Password does not match security requirements and/or passwords do not match. \n"
                    + "Password must be 8-20 characters long, contain at least one number, small and capital letter. \n"
                    + "Password must not contain any white marks.");
            return new ResponseEntity<>(messages, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "auth/confirm", params = "token")
    public ResponseEntity<String> confirmEmail(@RequestParam(name = "token") final String token) {
        //TODO Verification tokens
        return null;
    }

    @Autowired
    public AuthUserRegistrationController(AuthUserService userService) {
        this.userService = userService;
    }
}
