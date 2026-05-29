package com.Security.Securitytest.controller;

import com.Security.Securitytest.Entity.User;
import com.Security.Securitytest.Entity.UsersRecord;
import com.Security.Securitytest.Repositories.UserRepository;
import com.Security.Securitytest.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository repo;

    @PostMapping("/register")
    public User saveNewUser(@RequestBody  User user){
        return userService.saveNewUser(user);
    }

    @GetMapping("/allUsers")
    public List<UsersRecord> getAllUsers(){
        return userService.getAllUsers();
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<StringBuffer> removeUser(@PathVariable int id){
        Optional<User> user=repo.findById(id);
        StringBuffer msg=new StringBuffer();
        if(user.isPresent())
        {
            repo.deleteById(id);
            msg.append("user deleted successfully");
            return ResponseEntity.status(HttpStatus.OK).body(msg);
        }
        else{
            //throw new UsernameNotFoundException("user not found");
            msg.append("user not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        }
    }
}
