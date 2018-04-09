package com.moyou.moyouRms.service.secret;

import java.util.List;
import java.util.Map;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.secret.Secret;
import com.moyou.moyouRms.model.secret.SecretComment;
import com.moyou.moyouRms.model.secret.SecretInsertModel;

public interface SecretService {
    int deleteByPrimaryKey(Integer id);

    int insert(Secret record);

    int insertSelective(Secret record);

    Secret selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Secret record);

    int updateByPrimaryKey(Secret record);
 // 秘密
 	Integer updateUserCountSecretJian1(Integer userId);
    /**
     * 初始化秘密接口
     * @param pb userid 
     * @return
     */
    List<Secret> selectSecretByPageBeanParam(PageBean pb);
    /**
     * 秘密详情
     * @param record
     * @return
     */
    Secret selectSecretDetail(PageBean pb);

	Map<String,Object> selectSecretCount();

	Secret selectH5SecretDetail(PageBean pb);
	/**
	 * insert Secret
	 * 
	 * @param insertModel
	 * @return
	 */
	Integer insertSecret(SecretInsertModel insertModel);

	int insertComment(SecretComment secretComment);
/**
 * 修改阅读量
 * @param secret
 * @return
 */
int updateSecretReadCount(Secret secret);
}