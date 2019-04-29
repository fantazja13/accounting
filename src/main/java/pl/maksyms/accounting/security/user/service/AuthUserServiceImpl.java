package pl.maksyms.accounting.security.user.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.maksyms.accounting.security.role.Role;
import pl.maksyms.accounting.security.role.RoleNotFoundException;
import pl.maksyms.accounting.security.role.repository.RoleJPARepository;
import pl.maksyms.accounting.security.user.AuthUser;
import pl.maksyms.accounting.security.user.NewAuthUserDTO;
import pl.maksyms.accounting.security.user.repository.AuthUserJPARepository;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AuthUserServiceImpl implements AuthUserService {

    private final AuthUserJPARepository userRepository;
    private final RoleJPARepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private static final String USER_ROLE_NAME = "USER";
    private static final String PASSWORD_REGEXP = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,20}$";

    @Override
    public Optional<AuthUser> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<AuthUser> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<AuthUser> findAll() {
        return userRepository.findAll();
    }

    @Override
    public AuthUser save(AuthUser user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public AuthUser prepareNewUserFromDTO(NewAuthUserDTO newUserDto) {
        AuthUser user = new AuthUser();
        user.setUsername(newUserDto.getUsername());
        user.setPassword(passwordEncoder.encode(newUserDto.getPassword()));
        Set<Role> roles = Stream.of(roleRepository.findByName(USER_ROLE_NAME)
                .orElseThrow(()->new RoleNotFoundException("Role not found:"+USER_ROLE_NAME)))
                .collect(Collectors.toSet());
        user.setRoles(roles);
        user.setAccountNonExpired(true);
        user.setCredentialsNonExpired(true);
        user.setAccountNonLocked(true);
        //TODO setEnabled - FALSE
        user.setEnabled(true);
        return user;
    }

    @Override
    public boolean isPasswordValid(String password) {
        Pattern pattern = Pattern.compile(PASSWORD_REGEXP);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public AuthUserServiceImpl(AuthUserJPARepository userRepository, RoleJPARepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }
}
