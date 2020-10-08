package com.lixd.serviceverificationcode.controller;

import com.lixd.internalcommon.dto.ResponseResult;
import com.lixd.internalcommon.dto.serviceverificationcode.VerifyCodeResponse;
import com.lixd.serviceverificationcode.service.VerifyCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther:lixd
 * @Date:2020/10/7-10-07-16:47
 * @Description:com.lixd.serviceverificationcode.controller
 */
@RestController
@RequestMapping("/verify-code")
public class VerifyCodeController {
    @Autowired
    VerifyCodeService service;

    @RequestMapping("/generate/{identity}/{phoneNumber}")
    public ResponseResult<VerifyCodeResponse> generate(@PathVariable("identity") int identity,@PathVariable("phoneNumber") String phoneNumber){
        ResponseResult<VerifyCodeResponse> generate = service.generate(identity, phoneNumber);
        return generate;
    }
}
