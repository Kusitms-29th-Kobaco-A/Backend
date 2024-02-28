package core.kobaco.application.user.service;

import core.kobaco.application.user.service.dto.request.UserLoginRequest;
import core.kobaco.application.user.service.dto.response.UserLoginResponse;
import core.kobaco.domain.user.service.UserReader;
import core.kobaco.domain.user.service.UserValidator;
import core.kobaco.global.jwt.BearerTokenGenerator;
import core.kobaco.global.jwt.PrivateClaims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserReader userReader;
    private final UserValidator userValidator;
    private final BearerTokenGenerator bearerTokenGenerator;

    @Transactional(readOnly = true)
    public UserLoginResponse login(UserLoginRequest request){
        if(userValidator.existsByEmailAndPassword(request.email(), request.password())){
            PrivateClaims.UserClaims userClaims = PrivateClaims.UserClaims.of(userReader.read(request.email()).getId());
            return new UserLoginResponse(bearerTokenGenerator.generateAccessTokenAndAttachBearer(userClaims));
        }
        throw new RuntimeException("user not found"); // TODO : 예외 정의하기
    }
}