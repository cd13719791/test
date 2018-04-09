package com.moyou.moyouRms.service.secret;

import java.util.List;

import com.moyou.moyouRms.model.secret.SecretContent;

public interface SecretContentService {
    int deleteByPrimaryKey(Integer id);

    int insert(SecretContent record);

    int insertSelective(SecretContent record);

    SecretContent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SecretContent record);

    int updateByPrimaryKey(SecretContent record);
    List<SecretContent> selectSecretContentBySecretId(Integer id);
}