package com.lixd.serviceverificationcode.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lixd.internalcommon.dto.ResponseResult;
import com.lixd.internalcommon.dto.serviceverificationcode.VerifyCodeResponse;
import com.lixd.serviceverificationcode.service.VerifyCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Auther:lixd
 * @Date:2020/10/7-10-07-17:45
 * @Description:com.lixd.serviceverificationcode.service.impl
 */
@Service
public class VerifyCodeServiceImpl implements VerifyCodeService {
    @Autowired
    @Qualifier("jacksonRedisSerializer")
    StringRedisTemplate sredis;
    @Autowired
    ObjectMapper objectMapper;
    @Override
    public ResponseResult<VerifyCodeResponse> generate(int identity, String phoneNumber) {
        Object o = sredis.opsForHash().get(phoneNumber,phoneNumber);
        VerifyCodeResponse vc=new VerifyCodeResponse();
        if(o==null){
            String code=String.valueOf((int)((Math.random()*9+1)*Math.pow(10,5)));
            //redis.opsForValue().set(phoneNumber,code,60, TimeUnit.SECONDS);
            sredis.opsForHash().put(phoneNumber,phoneNumber,code);
            sredis.opsForHash().put(phoneNumber,"num",1);
            sredis.expire(phoneNumber,1,TimeUnit.MINUTES);
            vc.setCode(code);
            return ResponseResult.success(vc);
        }else {
            Object num = sredis.opsForHash().get(phoneNumber, "num");
            if((int)num<3){
                sredis.opsForHash().put(phoneNumber,"num",(int)num+1);
                vc.setCode(sredis.opsForHash().get(phoneNumber,phoneNumber).toString());
                return ResponseResult.success(vc);
            }else {
                return ResponseResult.fail(vc);
            }
        }
    }
}
