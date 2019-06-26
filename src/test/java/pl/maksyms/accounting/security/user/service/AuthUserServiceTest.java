package pl.maksyms.accounting.security.user.service;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import pl.maksyms.accounting.security.role.Role;
import pl.maksyms.accounting.security.role.repository.RoleJPARepository;
import pl.maksyms.accounting.security.user.AuthUser;
import pl.maksyms.accounting.security.user.AuthUserDTO;
import pl.maksyms.accounting.security.user.repository.AuthUserJPARepository;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthUserServiceTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();
    @Mock
    private AuthUserJPARepository authUserRepository;
    @Mock
    private RoleJPARepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private AuthUserService userService;

    private static final String username = "test@test.com";
    private static final String password = "P@ssw0rd";
    private static final String confirm = "P@ssw0rd";

    @Before
    public void setUp() {
        this.userService = new AuthUserServiceImpl(authUserRepository, roleRepository, passwordEncoder);
    }

    @Test
    public void prepareNewUserFromDTO_hasCorrectData() {
        Role role = new Role();
        role.setName("USER");
        Mockito.when(roleRepository.findByName("USER")).thenReturn(Optional.of(role));
        AuthUserDTO userDTO = new AuthUserDTO();
        userDTO.setUsername(username);
        userDTO.setPassword(password);
        userDTO.setConfirm(confirm);
        AuthUser user = userService.prepareNewUserFromDTO(userDTO);
        assertEquals(username, user.getUsername());
        assertTrue(passwordEncoder.matches(password, user.getPassword()));
        assertTrue(user.isAccountNonExpired());
        assertTrue(user.isAccountNonLocked());
        assertTrue(user.isCredentialsNonExpired());
        // TODO New user will be disabled by default when email verification is ready
        assertTrue(user.isEnabled());
    }

    @Test
    public void isPasswordValid_positive() {
        assertTrue(userService.isPasswordValid(password));
    }

    @Test
    public void isPasswordValid_negative_numberTest() {
        assertFalse(userService.isPasswordValid("Password"));
    }

    @Test
    public void isPasswordValid_negative_uppercaseTest() {
        assertFalse(userService.isPasswordValid("passw0rd"));
    }

    @Test
    public void isPasswordValid_negative_lowercaseTest() {
        assertFalse(userService.isPasswordValid("PASSW0RD"));
    }

    @Test
    public void isPasswordValid_negative_whiteMarkTest() {
        assertFalse(userService.isPasswordValid("Pass w0rd"));
    }

    @Test
    public void isPasswordValid_negative_8charactersTest() {
        assertFalse(userService.isPasswordValid("Passw0r"));
    }

    @Test
    public void isPasswordValid_negative_20charactersTest() {
        assertFalse(userService.isPasswordValid("Password1234567890!!!"));
    }

    @Test
    public void passwordMatchesConfirm_positive() {
        AuthUserDTO userDTO = new AuthUserDTO();
        userDTO.setUsername(username);
        userDTO.setPassword(password);
        userDTO.setConfirm(confirm);
        assertTrue(userService.passwordMatchesConfirm(userDTO));
    }

    @Test
    public void passwordMatchesConfirm_negative() {
        AuthUserDTO userDTO = new AuthUserDTO();
        userDTO.setUsername(username);
        userDTO.setPassword(password);
        userDTO.setConfirm("P@sswOrd");
        assertFalse(userService.passwordMatchesConfirm(userDTO));
    }

    @Test
    public void isUsernameTaken_positive() {
        Mockito.when(authUserRepository.findByUsername(username)).thenReturn(Optional.empty());
        assertFalse(userService.isUsernameTaken(username));
    }

    @Test
    public void isUsernameTaken_negative() {
        Mockito.when(authUserRepository.findByUsername(username)).thenReturn(Optional.of(new AuthUser()));
        assertTrue(userService.isUsernameTaken(username));
    }
}