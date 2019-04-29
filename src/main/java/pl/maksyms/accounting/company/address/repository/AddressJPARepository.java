package pl.maksyms.accounting.company.address.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.maksyms.accounting.company.address.Address;

public interface AddressJPARepository extends JpaRepository<Address, Long> {
}
