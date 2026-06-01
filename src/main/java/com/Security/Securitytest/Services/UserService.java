package com.Security.Securitytest.Services;

import com.Security.Securitytest.Entity.User;
import com.Security.Securitytest.Entity.UsersRecord;
import com.Security.Securitytest.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userrepo;

    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);

    public User saveNewUser(User user){
        user.setPassword(encoder.encode(user.getPassword()));
        //System.out.println("encoded : "+user.getPassword());
        return userrepo.save(user);
    }

    public List<UsersRecord> getAllUsers(){

        return userrepo.findAll()
                .stream()
                .map(user -> new UsersRecord(
                        user.getId(),
                        user.getUsername()
                ))
                .toList();
    }

    public StringBuffer forgotPassword(int userId,String newPassword,String confirmPassword){
        Optional<User> user1=userrepo.findById(userId) ;
        StringBuffer msg=new StringBuffer();
        if(newPassword.equals(confirmPassword) && user1.isPresent())
        {
            Optional<User> user = userrepo.findById(userId) ;
            if(user.isPresent()) {
                User usr=user.get();
                usr.setPassword(encoder.encode(confirmPassword));
                userrepo.save(usr);
                msg.append("new password Updated Successfully");
            }
        }
       return msg;
    }
}
