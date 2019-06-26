package pl.maksyms.accounting.security.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.maksyms.accounting.security.user.repository.AuthUserJPARepository;

@Service
public class AuthUserDetailsServiceImpl implements UserDetailsService {

    private final AuthUserJPARepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("No such user"));
    }

    @Autowired
    public AuthUserDetailsServiceImpl(AuthUserJPARepository userRepository) {
        this.userRepository = userRepository;
    }
}
