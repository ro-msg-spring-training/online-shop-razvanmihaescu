package ro.msg.learning.shop.services.login_service;

import ro.msg.learning.shop.dtos.LoginDto;

public interface ILoginService {
    LoginDto tryToLogin(LoginDto loginDto);
}
