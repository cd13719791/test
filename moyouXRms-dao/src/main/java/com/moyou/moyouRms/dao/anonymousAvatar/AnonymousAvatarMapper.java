package com.moyou.moyouRms.dao.anonymousAvatar;

import com.moyou.moyouRms.model.anonymousAvatar.AnonymousAvatar;

public interface AnonymousAvatarMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AnonymousAvatar record);

    int insertSelective(AnonymousAvatar record);

    AnonymousAvatar selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AnonymousAvatar record);

    int updateByPrimaryKey(AnonymousAvatar record);

	AnonymousAvatar selectRandomAvatar();
}