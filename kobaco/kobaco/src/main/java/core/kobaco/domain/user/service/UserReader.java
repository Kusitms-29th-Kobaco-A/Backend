package core.kobaco.domain.user.service;

import core.kobaco.domain.user.User;
import core.kobaco.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequiredArgsConstructor
public class UserReader {
    private final UserRepository userRepository;

    public User read(String email){
        return userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("user not found"));
    }
}
