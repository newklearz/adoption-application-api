package com.p5.adoptions.service.exceptions;

public class ValidationException  extends  RuntimeException{

    private ApiError apiError;

    public ValidationException(ApiError apiError) {
        this.apiError = apiError;
    }

    public ApiError getApiError() {
        return apiError;
    }

    public ValidationException setApiError(ApiError apiError) {
        this.apiError = apiError;
        return this;
    }
}
