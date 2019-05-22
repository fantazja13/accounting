package pl.maksyms.accounting.security.user.service;

import pl.maksyms.accounting.security.user.AuthUser;
import pl.maksyms.accounting.security.user.AuthUserDTO;

import java.util.List;
import java.util.Optional;

public interface AuthUserService {

    Optional<AuthUser> findById(Long id);

    Optional<AuthUser> findByUsername(String username);

    List<AuthUser> findAll();

    AuthUser save(AuthUser user);

    void deleteById(Long id);

    AuthUser prepareNewUserFromDTO(AuthUserDTO newUserDto);

    boolean isPasswordValid(String password);

    boolean passwordMatchesConfirm(AuthUserDTO authUserDTO);

    boolean isUsernameTaken(String username);
}
