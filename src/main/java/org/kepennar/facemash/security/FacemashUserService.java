package org.kepennar.facemash.security;

import java.util.List;

import javax.inject.Inject;

import org.kepennar.facemash.model.security.UserAccount;
import org.kepennar.facemash.repository.security.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.google.common.base.Preconditions;

/**
 * Database user authentication service.
 */
@Component
public final class FacemashUserService implements UserDetailsService {

    @Inject
    private UserRepository userRepository;
    
    /**
     * Loads the user from the datastore, by it's user name <br>
     */
    @Override
    public final UserDetails loadUserByUsername(final String username) {
        Preconditions.checkNotNull(username);

        final UserAccount user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Username was not found: " + username);
        }

        final List<GrantedAuthority> auths;
        if (!user.getRoles().isEmpty()) {
	    	auths = AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRolesCSV());
        } else {
        	auths = AuthorityUtils.NO_AUTHORITIES;
        }

        return new User(user.getUsername(), user.getPassword(), auths);
    }
}