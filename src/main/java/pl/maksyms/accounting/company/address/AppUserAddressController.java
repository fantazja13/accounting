package pl.maksyms.accounting.company.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.maksyms.accounting.company.address.service.AddressService;
import pl.maksyms.accounting.company.user.AppUser;
import pl.maksyms.accounting.company.user.dto.AppUserAddressDTO;
import pl.maksyms.accounting.company.user.service.AppUserService;
import pl.maksyms.accounting.security.user.AuthUser;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(path = "/secure/user/address")
public class AppUserAddressController {

    private final AppUserService userService;
    private final AddressService addressService;

    @GetMapping
    public ResponseEntity<?> getUserAddress(@AuthenticationPrincipal AuthUser authUser) {
        Optional<AppUser> optAppUser = userService.findByEmail(authUser.getUsername());
//        if (optAppUser.isPresent() && optAppUser.get().getAddress() != null) {
//            return new ResponseEntity<>(optAppUser.get().getAddress(), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
        AppUser user = optAppUser.orElse(new AppUser());
        return new ResponseEntity<>(addressService.findByCompanyId(user.getId()), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> addOrUpdateUserAddress(@Valid @RequestBody AppUserAddressDTO addressDTO,
                                                    @AuthenticationPrincipal AuthUser user) {
        Optional<AppUser> optAppUser = userService.findByEmail(user.getUsername());
        if (optAppUser.isPresent()) {
            AppUser appUser = optAppUser.get();
            Address address = addressService.prepareNewAddressFromDTO(addressDTO);
            appUser.setAddress(address);
            addressService.save(address);
            userService.save(appUser);
            return new ResponseEntity<>(address, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Autowired
    public AppUserAddressController(AppUserService userService, AddressService addressService) {
        this.userService = userService;
        this.addressService = addressService;
    }
}
