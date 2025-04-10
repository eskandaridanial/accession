package com.accession.io.exception;

import com.accession.io.common.contracts.classes.BaseException;
import com.accession.io.common.properties.ResponseProperties;
import com.accession.io.message.models.ReasonModel;
import com.accession.io.message.models.ResponseModel;
import lombok.Getter;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-31 22:40:56
 */
@Getter
public class RecordNotFoundException extends BaseException {

    private final ReasonModel reason;

    public RecordNotFoundException(ReasonModel reason) {
        super(code(), status(), message(), displayReason(), reason);
        this.reason = reason;
    }

    private static Integer code() {
        return model().code();
    }

    private static Integer status() {
        return model().status();
    }

    private static String message() {
        return model().message();
    }

    private static Boolean displayReason() {
        return model().displayReason();
    }

    private static ResponseModel model() {
        return ResponseProperties.of(RecordNotFoundException.class.getSimpleName());
    }
}