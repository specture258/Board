package com.backendApi.board.domain;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;


@Getter
public enum Grade {
    USER, ADMIN
}
