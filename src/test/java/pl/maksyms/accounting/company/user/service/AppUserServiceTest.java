package pl.maksyms.accounting.company.user.service;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.maksyms.accounting.company.address.Address;
import pl.maksyms.accounting.company.address.service.AddressService;
import pl.maksyms.accounting.company.user.AppUser;
import pl.maksyms.accounting.company.user.IncomeTaxType;
import pl.maksyms.accounting.company.user.dto.AppUserAddressDTO;
import pl.maksyms.accounting.company.user.dto.AppUserDTO;
import pl.maksyms.accounting.company.user.repository.AppUserJPARepository;
import pl.maksyms.accounting.security.user.AuthUser;
import pl.maksyms.accounting.security.user.repository.AuthUserJPARepository;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppUserServiceTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();
    private AppUserService appUserService;
    @Mock
    private AppUserJPARepository appUserRepository;
    @Mock
    private AuthUserJPARepository authUserRepository;
    @Mock
    private AddressService addressService;

    @Before
    public void setUp() {
        this.appUserService = new AppUserServiceImpl(appUserRepository, authUserRepository, addressService);
    }

    @Test
    public void prepareNewAppUserFromDTO_hasCorrectData() {
        String companyName = "company";
        String username = "username";
        String nip = "1231168095";
        AppUserDTO userDTO = new AppUserDTO();
        userDTO.setVATPayor(true);
        userDTO.setCompanyName(companyName);
        userDTO.setIncomeTax("flat rate");
        userDTO.setTaxNumber(nip);
        AuthUser authUser = new AuthUser();
        authUser.setUsername(username);
        AppUser user = appUserService.prepareNewAppUserFromDTO(authUser, userDTO);
        assertEquals(companyName, user.getName());
        assertTrue(user.isVATPayor());
        assertEquals(IncomeTaxType.FLAT_RATE, user.getIncomeTax());
        assertEquals(authUser, user.getAuthUser());
        assertEquals(nip, user.getTaxNumber());
    }

    @Test
    public void setAppUserAddressFromDTO_hasCorrectData() {
        String postalCode = "12-123";
        String town = "town";
        String street = "street";
        AppUser user = new AppUser();
        AppUserAddressDTO addressDTO = new AppUserAddressDTO();
        addressDTO.setPostalCode(postalCode);
        addressDTO.setTown(town);
        addressDTO.setStreet(street);
        Address address = new Address();
        address.setTown(town);
        address.setPostalCode(postalCode);
        address.setStreet(street);
        address.setCompany(user);
        Mockito.when(addressService.prepareNewAddressFromDTO(addressDTO)).thenReturn(address);
        appUserService.setAppUserAddressFromDTO(user, addressDTO);
        Address addressResut = user.getAddress();
        assertEquals(postalCode, addressResut.getPostalCode());
        assertEquals(town, addressResut.getTown());
        assertEquals(street, addressResut.getStreet());
        assertEquals(user, addressResut.getCompany());
    }
}