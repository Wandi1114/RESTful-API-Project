package com.test.danstest.services;

import com.test.danstest.models.User;
import com.test.danstest.models.dtos.LoginDTO;
import com.test.danstest.repositories.UserRepository;
import com.test.danstest.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    UserRepository userRepository;

    public ResponseEntity<Object> login(LoginDTO loginDTO){
        Optional<User> userCheck = userRepository.findByUsername(loginDTO.username);
        Map<String, Object> response = new HashMap<>();

        if(userCheck.isEmpty()){
            response.put("message", "User not found");
            response.put("payload", null);
            return ResponseEntity.badRequest().body(response);
        }
        User user = userCheck.get();
        //validasi password
        if(!user.getPassword().equals(loginDTO.password)){
            response.put("message", "Wrong password");
            response.put("payload", null);
            return ResponseEntity.badRequest().body(response);
        }
        response.put("message", "Welcome");
        response.put("payload", null);
        response.put("token", JwtUtil.generateToken(user));
        return ResponseEntity.ok(response);
    }
}
