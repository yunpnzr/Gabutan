package com.yunpznr.gabutan.repository;

import com.yunpznr.gabutan.entity.Otp;
import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public interface OtpRepository extends KeyValueRepository<Otp, String>{

}
