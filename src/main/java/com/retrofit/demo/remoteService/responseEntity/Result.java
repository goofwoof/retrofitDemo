package com.retrofit.demo.remoteService.responseEntity;

import lombok.*;

import java.io.Serializable;

/**
 * @author puthlive
 */
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> implements Serializable {
    private static final long serialVersionUID = -504027247149928390L;
    private int code;
    private String msg;
    private T data;
}
