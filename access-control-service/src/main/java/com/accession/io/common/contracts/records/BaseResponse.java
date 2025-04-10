package com.accession.io.common.contracts.records;

import com.accession.io.common.contracts.classes.BaseException;
import com.accession.io.message.models.ReasonModel;
import com.accession.io.message.models.ResponseModel;
import com.accession.io.common.properties.ResponseProperties;
import com.accession.io.message.values.UniqueId;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.HttpStatusCodeException;

import java.time.LocalDateTime;

/**
* @author: Danial Eskandari
* @createdAt: 2025-01-05 15:23:01
*/
public record BaseResponse<Result> (

        Integer code,

        String message,

        String referenceId,

        String timestamp,

        Result result
) {
    public BaseResponse(Integer code, String message, Result result) {
        this(code, message, UniqueId.of().getId(), LocalDateTime.now().toString(), result);
    }

    public static <Result> ResponseEntity<BaseResponse<Result>> of(Result result) {
        ResponseModel model = ResponseProperties.of();
        BaseResponse<Result> response = new BaseResponse<>(model.code(), model.message(), result);
        return ResponseEntity.status(model.status()).body(response);
    }

    public static ResponseEntity<BaseResponse<?>> of(BaseException exception) {
        ReasonModel reason = exception.getReason();
        String message = exception.getDisplayReason() && !ObjectUtils.isEmpty(reason) ? reason.message() : exception.getMessage();
        BaseResponse<?> response = new BaseResponse<>(exception.getCode(), message, null);
        return ResponseEntity.status(exception.getStatus()).body(response);
    }

    public static ResponseEntity<BaseResponse<?>> of(Exception exception) {
        ResponseModel model = ResponseProperties.of(exception.getClass().getSimpleName());
        BaseResponse<?> response = new BaseResponse<>(model.code(), model.message(), null);
        return ResponseEntity.status(model.status()).body(response);
    }

    public static ResponseEntity<BaseResponse<?>> of(HttpStatusCodeException exception) {
        BaseResponse<?> response = exception.getResponseBodyAs(BaseResponse.class);
        return ResponseEntity.status(exception.getStatusCode()).body(response);
    }
}
