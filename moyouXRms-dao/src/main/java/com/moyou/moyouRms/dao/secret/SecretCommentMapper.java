package com.moyou.moyouRms.dao.secret;

import java.util.List;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.secret.SecretComment;

public interface SecretCommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SecretComment record);

    int insertSelective(SecretComment record);

    SecretComment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SecretComment record);

    int updateByPrimaryKey(SecretComment record);
    /**
     * 根据秘密Id获取评论内容
     * @param pb
     * @return
     */
    List<SecretComment> selectSecretCommentBySecretId(PageBean pb);
}