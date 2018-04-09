package com.moyou.moyouRms.dao.secret;

import java.util.List;

import com.moyou.moyouRms.model.secret.SecretContent;

public interface SecretContentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SecretContent record);

    int insertSelective(SecretContent record);

    SecretContent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SecretContent record);

    int updateByPrimaryKey(SecretContent record);

	List<SecretContent> selectSecretContentBySecretId(Integer secretId);
}