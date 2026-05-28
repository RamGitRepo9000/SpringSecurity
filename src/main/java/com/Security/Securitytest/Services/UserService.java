package com.Security.Securitytest.Services;

import com.Security.Securitytest.Entity.User;
import com.Security.Securitytest.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userrepo;

    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);

    public User saveNewUser(User user){
        user.setPassword(encoder.encode(user.getPassword()));
        System.out.println("encoded : "+user.getPassword());
        return userrepo.save(user);
    }
}
