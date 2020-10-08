package com.lixd.internalcommon.dto;

import com.lixd.internalcommon.constant.CommonStatusEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Auther:lixd
 * @Date:2020/10/7-10-07-17:03
 * @Description:com.lixd.internalcommon.dto
 */
@Data
@Accessors(chain = true)
public class ResponseResult<T> implements Serializable {

    private static final long serialVersionUID = -7335170959315990364L;
    private int code;
    private String message;
    private T data;
    public static <T> ResponseResult success(T data){
        return new ResponseResult().setCode(CommonStatusEnum.SUCCESS.getCode()).setMessage(CommonStatusEnum.SUCCESS.getValue()).setData(data);
    }
    public static <T> ResponseResult fail(T data){
        return new ResponseResult().setCode(CommonStatusEnum.FAIL.getCode()).setMessage(CommonStatusEnum.FAIL.getValue()).setData(data);
    }
}
