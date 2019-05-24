package pl.maksyms.accounting.company.address.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.maksyms.accounting.company.address.Address;
import pl.maksyms.accounting.company.address.repository.AddressJPARepository;
import pl.maksyms.accounting.company.user.dto.AppUserAddressDTO;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressJPARepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressJPARepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Optional<Address> findById(Long id) {
        return addressRepository.findById(id);
    }

    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        addressRepository.deleteById(id);
    }

    @Override
    public Address prepareNewAddressFromDTO(AppUserAddressDTO dto) {
        Address address = new Address();
        address.setPostalCode(dto.getPostalCode());
        address.setStreet(dto.getStreet());
        address.setTown(dto.getTown());
        return address;
    }

    @Override
    public Optional<Address> findByCompanyId(Long id) {
        return addressRepository.findByCompanyId(id);
    }
}
