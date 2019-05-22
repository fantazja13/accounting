package pl.maksyms.accounting.company.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.maksyms.accounting.company.user.AppUser;
import pl.maksyms.accounting.company.user.IncomeTaxType;
import pl.maksyms.accounting.company.user.dto.AppUserAddressDTO;
import pl.maksyms.accounting.company.user.dto.AppUserDTO;
import pl.maksyms.accounting.company.user.repository.AppUserJPARepository;
import pl.maksyms.accounting.security.user.AuthUser;
import pl.maksyms.accounting.security.user.repository.AuthUserJPARepository;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserServiceImpl implements AppUserService {

    private final AppUserJPARepository userRepository;
    private final AuthUserJPARepository authUserRepository;

    @Override
    public AppUser save(AppUser user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<AppUser> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<AppUser> findByEmail(String email) {
        return userRepository.findByAuthUserUsername(email);
    }

    @Override
    public List<AppUser> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public AppUser prepareNewAppUserFromDTO(AuthUser authUser, AppUserDTO dto) {
        AppUser newUser = new AppUser();
        newUser.setAuthUser(authUser);
        newUser.setName(dto.getCompanyName());
        newUser.setVATPayor(dto.isVATPayor());
        newUser.setTaxNumber(dto.getTaxNumber());
        newUser.setIncomeTax(IncomeTaxType.fromString(dto.getIncomeTax()));
        return newUser;
    }

    @Override
    public AppUser setAppUserAddressFromDTO(AppUser user, AppUserAddressDTO addressDTO) {
        return null;
    }

    @Autowired
    public AppUserServiceImpl(AppUserJPARepository userRepository, AuthUserJPARepository authUserRepository) {
        this.userRepository = userRepository;
        this.authUserRepository = authUserRepository;
    }

}
