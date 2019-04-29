package pl.maksyms.accounting.security.role.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.maksyms.accounting.security.role.Role;
import pl.maksyms.accounting.security.role.repository.RoleJPARepository;

@Service
public class RoleServiceImpl implements RoleService{

    private final RoleJPARepository repository;

    @Override
    public Role save(Role role) {
        return repository.save(role);
    }

    @Autowired
    public RoleServiceImpl(RoleJPARepository repository) {
        this.repository = repository;
    }
}
