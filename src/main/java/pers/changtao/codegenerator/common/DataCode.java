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
 * 返回结果枚举类
 * </p>
 *
 * @Author: Alex
 * @Email: changtao6605@gmail.com
 * @Date: 2021-05-11
 */
public enum DataCode {
    SUCCESS(200, "访问成功"),
    UNAUTHORIZED(401, "未授权或未登录"),
    FORBIDDEN(403, "拒绝访问"),
    NOFOUND(404, "没有找到资源"),
    SERVERERROR(500, "服务器错误");

    private int code;
    private String message;

    /**
     * 构造方法
     * @param code
     * @param message
     */
    private DataCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
