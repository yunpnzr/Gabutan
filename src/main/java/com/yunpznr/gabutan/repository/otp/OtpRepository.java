package com.yunpznr.gabutan.repository.otp;

import com.yunpznr.gabutan.entity.Otp;
import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtpRepository extends KeyValueRepository<Otp, String>{

}
