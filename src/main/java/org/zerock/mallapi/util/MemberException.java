package org.zerock.mallapi.util;

import org.zerock.mallapi.domain.ErrorCode;

public class MemberException extends CustomException {

    public MemberException(ErrorCode errorCode) {
        super(errorCode);
    }

}
