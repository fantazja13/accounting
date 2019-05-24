package pl.maksyms.accounting.company.address.service;

import pl.maksyms.accounting.company.address.Address;
import pl.maksyms.accounting.company.user.dto.AppUserAddressDTO;

import java.util.List;
import java.util.Optional;

public interface AddressService {
    Address save(Address address);
    Optional<Address> findById(Long id);
    List<Address> findAll();
    void deleteById(Long id);
    Address prepareNewAddressFromDTO(AppUserAddressDTO dto);
    Optional<Address> findByCompanyId(Long id);
}
