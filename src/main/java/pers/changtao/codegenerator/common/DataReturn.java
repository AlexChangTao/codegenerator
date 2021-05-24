/*
 *  Copyright 2020-2021 ChangTao
 *  Email changtao6605@gmail.com
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package pers.changtao.codegenerator.common;

/**
 * <p>
 * 公用数据返回类
 * </p>
 *
 * @Author: Alex
 * @Email: changtao6605@gmail.com
 * @Date: 2021-05-11
 */
public class DataReturn<T> {
    private int code;
    private String message;
    private T result;

    protected DataReturn() {
    }

    protected DataReturn(int code, String message, T result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

    /**
     * 返回错误自定义提示结果
     * @param message 提示信息
     */
    public static <T> DataReturn<T> failed(String message) {
        return new DataReturn<T>(DataCode.SERVERERROR.getCode(), message, null);
    }

    /**
     * 服务器错误返回结果
     */
    public static <T> DataReturn<T> failed() {
        return failed(DataCode.SERVERERROR);
    }

    /**
     * 没有找到资源返回结果
     */
    public static <T> DataReturn<T> validateFailed() {
        return failed(DataCode.NOFOUND);
    }

    /**
     * 没有找到资源自定义信息返回结果
     * @param message 提示信息
     */
    public static <T> DataReturn<T> validateFailed(String message) {
        return new DataReturn<T>(DataCode.NOFOUND.getCode(), message, null);
    }

    /**
     * 未授权或未登录返回结果
     */
    public static <T> DataReturn<T> unauthorized(T result) {
        return new DataReturn<T>(DataCode.UNAUTHORIZED.getCode(), DataCode.UNAUTHORIZED.getMessage(), result);
    }

    /**
     * 拒绝访问返回结果
     */
    public static <T> DataReturn<T> forbidden(T result) {
        return new DataReturn<T>(DataCode.FORBIDDEN.getCode(), DataCode.FORBIDDEN.getMessage(), result);
    }

    /**
     * 返回错误结果
     * @param dataCode 错误码
     */
    public static <T> DataReturn<T> failed(DataCode dataCode) {
        return new DataReturn<T>(dataCode.getCode(), dataCode.getMessage(), null);
    }

    /**
     * 返回错误结果
     * @param dataCode 错误码
     * @param message  提示信息
     */
    public static <T> DataReturn<T> failed(DataCode dataCode, String message) {
        return new DataReturn<T>(dataCode.getCode(), message,null);
    }

    /**
     * 返回成功的默认访回
     *
     * @param result 结果数据
     */
    public static <T> DataReturn<T> success(T result) {
        return new DataReturn<T>(DataCode.SUCCESS.getCode(), DataCode.SUCCESS.getMessage(), result);
    }

    /**
     * 返回成功自定义提示信息
     *
     * @param  message 提示信息
     * @param result 结果数据
     */
    public static <T> DataReturn<T> success(T result, String message) {
        return new DataReturn<T>(DataCode.SUCCESS.getCode(), message, result);
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
