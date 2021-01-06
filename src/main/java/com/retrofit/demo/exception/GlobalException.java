package com.retrofit.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GlobalException extends RuntimeException {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -5212079010855161498L;
    // 异常信息码
    private String code;
    // 异常信息
    private String message;
}
