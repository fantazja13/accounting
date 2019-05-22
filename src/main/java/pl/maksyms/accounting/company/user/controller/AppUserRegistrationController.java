package pl.maksyms.accounting.company.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.maksyms.accounting.company.user.AppUser;
import pl.maksyms.accounting.company.user.dto.AppUserDTO;
import pl.maksyms.accounting.company.user.service.AppUserService;
import pl.maksyms.accounting.security.user.AuthUser;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(path = "secure/user")
@PreAuthorize("hasAuthority('USER')")
public class AppUserRegistrationController {

    private final AppUserService userService;

    @GetMapping
    public ResponseEntity<?> getUser(@AuthenticationPrincipal AuthUser user) {
        Optional<AppUser> optionalAppUser = userService.findByEmail(user.getUsername());
        return new ResponseEntity<>(optionalAppUser.orElse(null), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> applicationUserRegistration(@Valid @RequestBody final AppUserDTO userDTO,
                                                         @AuthenticationPrincipal AuthUser authUser) {
        AppUser savedUser = userService.save(userService.prepareNewAppUserFromDTO(authUser, userDTO));
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @Autowired
    public AppUserRegistrationController(AppUserService userService) {
        this.userService = userService;
    }
}
