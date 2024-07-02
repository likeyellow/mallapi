package org.zerock.mallapi.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    SUCCESS(200, "Success"),
    
    INVALID_TOKEN(401, "Token이 유효하지 않습니다."),
    ACCESS_DENIED(403, "접근 권한이 없습니다."),

    MEMBER_NOT_FOUND(404, "존재하지 않는 회원입니다."),
    AUTHENTICATION_FAILED(400, "아이디 또는 비밀번호가 맞지 않습니다."),

    BOARD_NOT_FOUND(404, "존재하지 않는 게시물입니다."),
    BOARD_AUTHOR_MISMATCH(403, "해당 게시물에 접근 권한이 없습니다."),
    METHOD_NOT_ALLOWED_RESPONSE(405, "Method Not Allowed"),

    NOT_VALID_RESPONSE(409, "Not Valid"),
    NOT_FOUND_DATA_RESPONSE(409, "Not Found Data"),
    ALREADY_DATA_RESPONSE(409, "Already Data"),
    PARSE_ERROR_RESPONSE(409, "Parsing Error"),
    NOT_PRIMARY_ERROR_RESPONSE(409, "Not Primary Key Error"),
    ILLEGAL_ACCESS_RESPONSE(409, "SQL Error"),

    TO_MANY_REQUESTS_RESPONSE(429, "Too Many Requests");

    private final int status;
    private final String msg;
}
