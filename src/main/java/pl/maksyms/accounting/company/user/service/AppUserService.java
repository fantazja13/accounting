package pl.maksyms.accounting.company.user.service;

import pl.maksyms.accounting.company.user.AppUser;
import pl.maksyms.accounting.company.user.dto.AppUserAddressDTO;
import pl.maksyms.accounting.company.user.dto.AppUserDTO;
import pl.maksyms.accounting.security.user.AuthUser;

import java.util.List;
import java.util.Optional;

public interface AppUserService {

    AppUser save(AppUser user);

    Optional<AppUser> findById(Long id);

    Optional<AppUser> findByEmail(String email);

    List<AppUser> findAll();

    void deleteById(Long id);

    AppUser prepareNewAppUserFromDTO(AuthUser authUser, AppUserDTO dto);

    void setAppUserAddressFromDTO(AppUser user, AppUserAddressDTO addressDTO);

}
