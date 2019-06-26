package pl.maksyms.accounting.company.address.service;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.maksyms.accounting.company.address.Address;
import pl.maksyms.accounting.company.address.repository.AddressJPARepository;
import pl.maksyms.accounting.company.user.dto.AppUserAddressDTO;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressServiceTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();
    @Mock
    private AddressJPARepository addressRepository;

    private AddressService addressService;

    @Before
    public void setUp() {
        this.addressService = new AddressServiceImpl(addressRepository);
    }

    @Test
    public void prepareNewAddressFromDTO_hasCorrectData() {
        String street = "Street";
        String town = "Town";
        String postalCode = "24-123";
        AppUserAddressDTO addressDTO = new AppUserAddressDTO();
        addressDTO.setStreet(street);
        addressDTO.setTown(town);
        addressDTO.setPostalCode(postalCode);
        Address address = addressService.prepareNewAddressFromDTO(addressDTO);
        assertEquals(street, address.getStreet());
        assertEquals(town, address.getTown());
        assertEquals(postalCode, address.getPostalCode());
    }
}