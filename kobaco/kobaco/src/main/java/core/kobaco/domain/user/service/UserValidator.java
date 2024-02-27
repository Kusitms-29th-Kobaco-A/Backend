package core.kobaco.domain.user.service;

import core.kobaco.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequiredArgsConstructor
public class UserValidator {
    private final UserRepository userRepository;

    public Boolean existsByEmailAndPassword(String email, String password){
        return userRepository.existsByEmailAndPassword(email, password);
    }
}
