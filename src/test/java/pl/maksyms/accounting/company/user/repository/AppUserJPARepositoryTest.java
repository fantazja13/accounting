package pl.maksyms.accounting.company.user.repository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import pl.maksyms.accounting.company.user.AppUser;
import pl.maksyms.accounting.security.role.Role;
import pl.maksyms.accounting.security.user.AuthUser;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AppUserJPARepositoryTest {

    @Autowired
    TestEntityManager entityManager;
    @Autowired
    AppUserJPARepository userRepository;

    public static final String username = "test@test.com";
    public static final String notExistingUsername = "fake@email.com";

    @Before
    public void loadData() {
        AuthUser authUser = new AuthUser();
        authUser.setUsername(username);
        entityManager.persist(authUser);
        AppUser appUser = new AppUser();
        appUser.setAuthUser(authUser);
        appUser.setName("test");
        entityManager.persist(appUser);
    }

    @Test
    public void findByAuthUserUsername_isNotNull() {
        Optional<AppUser> optAppUser = userRepository.findByAuthUserUsername(username);
        Assert.assertTrue(optAppUser.isPresent());
    }

    @Test
    public void findByAuthUserUsername_hasCorrectUsername() {
        AppUser appUser = userRepository.findByAuthUserUsername(username).orElseThrow(()-> new Error("AppUser is null!"));
        Assert.assertEquals(username, appUser.getAuthUser().getUsername());
    }

    @Test
    public void findByAuthUserUsername_negative_isNull() {
        Optional<AppUser> optAppUser = userRepository.findByAuthUserUsername(notExistingUsername);
        Assert.assertFalse(optAppUser.isPresent());
    }
}