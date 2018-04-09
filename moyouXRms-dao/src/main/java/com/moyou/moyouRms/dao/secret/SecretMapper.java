package com.moyou.moyouRms.dao.secret;

import java.util.List;
import java.util.Map;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.secret.Secret;
import com.moyou.moyouRms.model.secret.SecretContentInsertModel;
import com.moyou.moyouRms.model.secret.SecretInsertModel;

public interface SecretMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Secret record);

	int insertSelective(Secret record);

	Secret selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Secret record);

	int updateByPrimaryKey(Secret record);

	/**
	 * 匿名头像 id
	 * 
	 * @return
	 */
	Integer queryAnonymousAvatarId();

	/**
	 * insert t_secret
	 * 
	 * @param insertModel
	 * @return
	 */
	int insertSecret(SecretInsertModel insertModel);

	/**
	 * insert t_secret_content
	 * 
	 * @param models
	 * @return
	 */
	int insertSecretContentList(List<SecretContentInsertModel> models);

	/**
	 * 初始化秘密接口
	 * 
	 * @param pb
	 *            userid
	 * @return
	 */
	List<Secret> selectSecretByPageBeanParam(PageBean pb);

	Map<String, Object> selectSecretCount();

	/**
	 * 秘密详情 用户数据
	 * 
	 * @param id
	 * @return
	 */
	Secret selectSecretDetail(Integer id);

	int updateCommentTotalJIA1(Integer id);

	Map<String, Object> queryCountSecret();

	int updateSecretReadCount(Secret secret);

}