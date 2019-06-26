package pl.maksyms.accounting.company.address.repository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import pl.maksyms.accounting.company.Company;
import pl.maksyms.accounting.company.address.Address;
import pl.maksyms.accounting.company.user.AppUser;
import pl.maksyms.accounting.security.user.AuthUser;

import javax.swing.text.html.Option;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AddressJPARepositoryTest {

    @Autowired
    TestEntityManager entityManager;
    @Autowired
    AddressJPARepository addressRepository;

    public static final String street = "street";
    public static final String town = "town";
    public static final String postalCode = "00-000";
    public long id;
    public static final long notExistingId = 999;

    @Before
    public void loadData() {
        AppUser appUser = new AppUser();
        Address address = new Address();
        address.setCompany(appUser);
        address.setStreet(street);
        address.setPostalCode(postalCode);
        address.setTown(town);
        appUser.setName("test");
        appUser.setAddress(address);
        AuthUser authUser = new AuthUser();
        appUser.setAuthUser(authUser);
        entityManager.persist(authUser);
        entityManager.persist(address);
        entityManager.persist(appUser);
        this.id = appUser.getId();
    }

    @Test
    public void findByCompanyId_IsNotNull() {
        Optional<Address> optAddress = addressRepository.findByCompanyId(id);
        Assert.assertTrue(optAddress.isPresent());
    }

    @Test
    public void findByCompanyId_hasCorrectData() {
        Address address = addressRepository.findByCompanyId(id).orElseThrow(()-> new Error("Address is null!"));
        Assert.assertEquals(town, address.getTown());
        Assert.assertEquals(street, address.getStreet());
        Assert.assertEquals(postalCode, address.getPostalCode());
    }

    @Test
    public void findByCompanyId_negative_isNull() {
        Optional<Address> optAddress = addressRepository.findByCompanyId(notExistingId);
        assertFalse(optAddress.isPresent());
    }
}