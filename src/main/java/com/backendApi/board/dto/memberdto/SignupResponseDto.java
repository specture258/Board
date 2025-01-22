package com.backendApi.board.dto.memberdto;


import lombok.Data;

@Data
public class SignupResponseDto {

    private String username;

    private String nickname;

    public SignupResponseDto(String username, String nickname) {
        this.username = username;
        this.nickname = nickname;
    }
}
