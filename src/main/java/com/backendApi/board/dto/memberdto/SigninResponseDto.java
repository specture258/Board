package com.backendApi.board.dto.memberdto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SigninResponseDto {
    private String username;

    private String nickname;

    private String redirectURL;

    //private String message;

    public SigninResponseDto(String username, String nickname) {
        this.username = username;
        this.nickname = nickname;
    }

    public SigninResponseDto(String username, String nickname, String redirectURL) {
        this.username = username;
        this.nickname = nickname;
        this.redirectURL = redirectURL;
    }

}
