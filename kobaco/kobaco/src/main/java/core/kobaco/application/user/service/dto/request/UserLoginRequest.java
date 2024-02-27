package core.kobaco.application.user.service.dto.request;

public record UserLoginRequest(
    String email,
    String password
) {
}
