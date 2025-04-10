package com.accession.io.common.contracts.classes;

import com.accession.io.message.models.ReasonModel;
import lombok.Getter;

/**
 * @author: Danial Eskandari
 * @createdAt: 2025-01-06 13:42:20
 */
@Getter
public abstract class BaseException extends RuntimeException {

    private final Integer code;
    private final Integer status;
    private final String message;
    private final ReasonModel reason;
    private final Boolean displayReason;

    public BaseException(Integer code, Integer status, String message, Boolean displayReason, ReasonModel reason) {
        super(message);
        this.code = code;
        this.status = status;
        this.message = message;
        this.displayReason = displayReason;
        this.reason = reason;
    }
}
