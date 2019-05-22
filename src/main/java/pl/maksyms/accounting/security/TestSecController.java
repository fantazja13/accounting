package pl.maksyms.accounting.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.maksyms.accounting.security.role.Role;
import pl.maksyms.accounting.security.role.service.RoleService;
import pl.maksyms.accounting.security.user.AuthUser;
import pl.maksyms.accounting.security.user.AuthUserDTO;
import pl.maksyms.accounting.security.user.service.AuthUserService;

@RestController
@RequestMapping(path = "/auth")
public class TestSecController {

    private final AuthUserService userService;
    private final RoleService roleService;

//    @PostMapping
//    @ResponseStatus(HttpStatus.OK)
//    public void signIn() {
//
//    }

    @GetMapping
    public String initUser() {
        Role role = new Role();
        role.setName("USER");
        roleService.save(role);
        AuthUserDTO userDto = new AuthUserDTO();
        userDto.setUsername("test@test.com");
        userDto.setPassword("Haslo123");
        userDto.setConfirm("Haslo123");
        AuthUser user = userService.prepareNewUserFromDTO(userDto);
        userService.save(user);
        return "Success";
    }

    @Autowired
    public TestSecController(AuthUserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
}
