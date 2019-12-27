package repair_system.services.repositoryServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import repair_system.dtos.SecureUser;
import repair_system.facade.UserFacade;
import repair_system.models.User;
import repair_system.repositories.UsersRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

/**
 * @author Yuliia Shcherbakova ON 08.12.2019
 * @project repair_system
 */
@Service
public class UsersService implements UserDetailsService {

    @Autowired
    private UsersRepository repository;
    @Autowired
    private UserFacade facade;

    public Optional<User> getUserByEmail(String email) {
        return repository.getUserByEmail(email);
    }

    public User getOneById(Integer userId) {
        return repository.getOneByUserId(userId);
    }

    @Override
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {

        User user = getUserByEmail(username).orElseThrow(() ->
                new UsernameNotFoundException("user " + username + " was not found!"));


        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRole()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(String role) {
        Collection<GrantedAuthority> roles = new ArrayList<>();
        SimpleGrantedAuthority e = new SimpleGrantedAuthority(role);
        roles.add(e);
        return roles;
    }

    public void add(User user) {
        repository.saveAndFlush(user);
    }
}
