package com.lixd.serviceverificationcode.service;

import com.lixd.internalcommon.dto.ResponseResult;
import com.lixd.internalcommon.dto.serviceverificationcode.VerifyCodeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @Auther:lixd
 * @Date:2020/10/7-10-07-17:40
 * @Description:com.lixd.serviceverificationcode.service
 */

public interface VerifyCodeService {

    ResponseResult<VerifyCodeResponse> generate(int identity , String phoneNumber);
}
