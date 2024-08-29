package org.frank.cloud.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ReturnCodeEnum {
    /**操作失敗**/
    RC999("999", "操作 xxx 失敗"),

    /**操作成功**/
    RC200("200", "success"),

    /**服務降級**/
    RC201("201", "服務開啟降級保護，請稍後再試"),

    /**熱點參數限流**/
    RC202("202", "熱點參數限流，請稍後再試"),

    /**系統規則不滿足**/
    RC203("203", "系統規則不滿足要求，請稍後再試"),

    /**授權規則不通過**/
    RC204("204", "授權規則不通過，請稍後再試"),

    /**匿名用戶**/
    RC401("401", "匿名用戶"),

    /**無存取權限**/
    RC403("403", "無訪問權限，請聯絡系統管理員授權"),

    /**404**/
    RC404("404", "找不到資源"),

    /**服務異常**/
    RC500("500", "服務異常，請稍後再試"),

    INVALID_TOKEN("2001", "訪問token不合法"),

    ACCESS_DENIED("2003", "無權限訪問該資源"),

    USERNAME_OR_PASSWORD_ERROR("1001", "客戶端認證失敗"),

    UNSUPPORTED_GRANT_TYPE("1003", "不支援的認證模式"),

    BUSINESS_ERROR("1004", "業務邏輯異常");

    private final String code;

    private final String message;

    ReturnCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    // v1 for 遍歷查找
    public static ReturnCodeEnum getReturnCodeEnumV1(String code) {
        for (ReturnCodeEnum element : ReturnCodeEnum.values()) {
            if (element.getCode().equalsIgnoreCase(code)) {
                return element;
            }
        }
        return null;
    }

    // v2 stream 查找
    public static ReturnCodeEnum getReturnCodeEnumV2(String code) {
        return Arrays.stream(ReturnCodeEnum.values())
                .filter(v -> v.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElse(null);
    }
}
