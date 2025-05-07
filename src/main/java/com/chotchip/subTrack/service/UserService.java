package com.chotchip.subTrack.service;

import com.chotchip.subTrack.dto.CreateUserDTO;
import com.chotchip.subTrack.dto.UpdateUserDTO;
import com.chotchip.subTrack.entity.User;
import com.chotchip.subTrack.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User save(CreateUserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.email());
        user.setPassword(userDTO.password());
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User getUserById(UUID id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Transactional
    public User updateUser(UUID id, UpdateUserDTO user) {
        User userFind = userRepository.findById(id).get();
        userFind.setEmail(user.email());
        userFind.setPassword(user.password());
        return userFind;
    }

    @Transactional
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
}
