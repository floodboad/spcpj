package com.ocean.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Result<T> {
    private boolean success;
    private T body;
    private Error error;

    public Result() {
    }

    public Result(boolean success, Error error, T body) {
        this.success = success;
        this.error = error;
        this.body = body;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getBody() {
        return this.body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public Error getError() {
        return this.error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public static Result.Builder builder() {
        return new Result.BuilderImpl();
    }

    private static class BuilderImpl implements Result.Builder {
        private boolean success;
        private Object body;
        private Error error;

        private BuilderImpl() {
        }

        @Override
        public Result.Builder body(Object body) {
            this.body = body;
            return this;
        }

        @Override
        public Result.Builder success(boolean success) {
            this.success = success;
            return this;
        }

        @Override
        public Result.Builder error(ExceptionEnum exceptionInfoEnum) {
            this.error = new Error(exceptionInfoEnum);
            return this;
        }

        @Override
        public Result.Builder error(String code, String message) {
            this.error = new Error(code, message);
            return this;
        }

        @Override
        public <T> Result<T> build() {
            return new Result(this.success, this.error, this.body);
        }
    }

    public interface Builder {
        Result.Builder success(boolean var1);

        Result.Builder body(Object var1);

        Result.Builder error(ExceptionEnum var1);

        Result.Builder error(String var1, String var2);

        <T> Result<T> build();
    }
}

