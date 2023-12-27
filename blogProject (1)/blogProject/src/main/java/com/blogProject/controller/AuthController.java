package com.blogProject.controller;

import com.blogProject.entity.Role;
import com.blogProject.entity.User;
import com.blogProject.jwt.JWTAuthResponse;
import com.blogProject.jwt.JwtTokenProvider;
import com.blogProject.payLoad.LoginDto;
import com.blogProject.payLoad.SignUpDto;
import com.blogProject.repository.RoleRepository;
import com.blogProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("api/auth")

public class AuthController {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired//For SignIn
    AuthenticationManager authenticationManager;
    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/signUp")
    public ResponseEntity<?>signupUser(@RequestBody SignUpDto signUpDto){
        if(userRepo.existsByEmail(signUpDto.getEmail())){
            return new ResponseEntity<>("email already present", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(userRepo.existsByusername(signUpDto.getUsername())){
            return new ResponseEntity<>("Username already present", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        User user=new User();
        user.setName(signUpDto.getName());
        user.setEmail(signUpDto.getEmail());
        user.setUsername(signUpDto.getUsername());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        //By default all signup it create ADMIN role
        Role roles=roleRepo.findByName("ROLE_ADMIN").get();
        Set<Role> role=new HashSet<>();
        role.add(roles);
        user.setRoles(role);
        userRepo.save(user);
        return  new ResponseEntity<>("user register...!",HttpStatus.CREATED);
    }


    @PostMapping("/signIn")
    public ResponseEntity<JWTAuthResponse> authenticateUser(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // get token form tokenProvider
        String token = tokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JWTAuthResponse(token));

    }
    }
