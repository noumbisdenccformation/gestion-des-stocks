package com.groupeO.gestiondestock.service;

import com.groupeO.gestiondestock.dto.AuthResponseDto;
import com.groupeO.gestiondestock.dto.LoginRequestDto;
import com.groupeO.gestiondestock.dto.RegisterRequestDto;

public interface AuthService {

    AuthResponseDto register(RegisterRequestDto request);

    AuthResponseDto login(LoginRequestDto request);

}
