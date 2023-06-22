package com.ibm.shop.services;

import com.ibm.shop.data.vo.LoginVO;
import com.ibm.shop.data.vo.RegisterVO;
import com.ibm.shop.entities.Role;
import com.ibm.shop.entities.User;
import com.ibm.shop.exceptions.IbmShopApiException;
import com.ibm.shop.repositories.RoleRepository;
import com.ibm.shop.repositories.UserRepository;
import com.ibm.shop.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public String login(LoginVO loginVO) {

        Authentication authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginVO.getUsernameOrEmail(),
                                loginVO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtTokenProvider.generateToken(authentication);
    }

    public String register(RegisterVO registerVO) {

        // Check if this user already exists
        if (userRepository.existsByUsername(registerVO.getUsername())) {
            throw new IbmShopApiException(HttpStatus.BAD_REQUEST, "Username is already exists!");
        }

        // Check if this user email already exists
        if (userRepository.existsByEmail(registerVO.getEmail())) {
            throw new IbmShopApiException(HttpStatus.BAD_REQUEST, "Email is already exists!");
        }

        User user = new User();

        user.setName(registerVO.getName());
        user.setEmail(registerVO.getEmail());
        user.setUsername(registerVO.getUsername());
        user.setPassword(passwordEncoder.encode(registerVO.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        roles.add(userRole);
        user.setRoles(roles);

        userRepository.save(user);

        return "User registered successfully!.";
    }
}