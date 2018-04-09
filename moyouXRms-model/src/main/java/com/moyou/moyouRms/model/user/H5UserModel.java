package com.moyou.moyouRms.model.user;

import com.moyou.moyouRms.model.BaseModel;

/**
 * @author 作者:陈旭
 * @version 创建时间：2017年7月28日 下午2:46:55 类说明
 */
public class H5UserModel extends BaseModel
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String avatar;
    private String nickname;
    private String sig;
    private Integer userId;

    public String getAvatar()
    {
        return avatar;
    }

    public void setAvatar(String avatar)
    {
        this.avatar = avatar;
    }

    public String getNickname()
    {
        return nickname;
    }

    public void setNickname(String nickname)
    {
        this.nickname = nickname;
    }

    public String getSig()
    {
        return sig;
    }

    public void setSig(String sig)
    {
        this.sig = sig;
    }

    public Integer getUserId()
    {
        return userId;
    }

    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }

}
