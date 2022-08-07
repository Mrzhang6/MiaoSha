package com.zd.seckill.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 *
 * @author 作者
 * @date 2022-04-27
 */
@TableName("t_user")
@ApiModel(value = "", description = "")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 用户ID shoujihaoma **/
    @ApiModelProperty("用户ID shoujihaoma")
      private Long id;

    private String nickname;

    /** MD5二次加密 **/
    @ApiModelProperty("MD5二次加密")
    private String password;

    private String salt;

    /** 头像 **/
    @ApiModelProperty("头像")
    private String head;

    /** 注册时间 **/
    @ApiModelProperty("注册时间")
    private Date registerDate;

    /** 最后一次登录时间 **/
    @ApiModelProperty("最后一次登录时间")
    private Date lastLoginDate;

    /** 登录次数 **/
    @ApiModelProperty("登录次数")
    private Integer loginCount;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    @Override
    public String toString() {
        return "User{" +
        "id=" + id +
        ", nickname=" + nickname +
        ", password=" + password +
        ", salt=" + salt +
        ", head=" + head +
        ", registerDate=" + registerDate +
        ", lastLoginDate=" + lastLoginDate +
        ", loginCount=" + loginCount +
        "}";
    }
}
