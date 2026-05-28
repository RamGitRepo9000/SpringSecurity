package com.Security.Securitytest.Services;

import com.Security.Securitytest.Entity.User;
import com.Security.Securitytest.Repositories.UserRepository;
import com.Security.Securitytest.UserPrinicipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    public UserRepository userrepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userrepo.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("user not found 404");
        }
        return new UserPrinicipal(user);
    }
}
