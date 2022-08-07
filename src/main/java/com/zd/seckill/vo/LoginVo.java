package com.zd.seckill.vo;

import com.zd.seckill.valiator.IsMobile;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @author zd
 * @date 2022/4/28 10:26
 * 描述：登录参数
 */
@Data
public class LoginVo {
    @NotNull
    @IsMobile
    private String mobile;

    @NotNull
    @Length(min = 32)
    private String password;

}
