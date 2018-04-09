package com.moyou.moyouRms.service.secret.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moyou.moyouRms.dao.secret.SecretCommentMapper;
import com.moyou.moyouRms.dao.secret.SecretMapper;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.secret.Secret;
import com.moyou.moyouRms.model.secret.SecretComment;
import com.moyou.moyouRms.service.secret.SecretCommentService;

@Service
public class SecretCommentServiceImpl implements SecretCommentService {
	@Resource
	private SecretCommentMapper secretCommentMappere;
	@Resource
	private SecretMapper secretMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return secretCommentMappere.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(SecretComment record) {
		// TODO Auto-generated method stub
		return secretCommentMappere.insert(record);
	}

	@Override
	public int insertSelective(SecretComment record) {
		// TODO Auto-generated method stub
		return secretCommentMappere.insertSelective(record);
	}

	@Override
	public SecretComment selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return secretCommentMappere.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(SecretComment record) {
		// TODO Auto-generated method stub
		SecretComment record2 = secretCommentMappere.selectByPrimaryKey(record.getId());
		record.setState(SecretComment.DELETE);
		this.secretCommentjiajian1(-1,record2.getSecretId());
		return secretCommentMappere.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(SecretComment record) {
		// TODO Auto-generated method stub
		return secretCommentMappere.updateByPrimaryKey(record);
	}

	@Override
	public List<SecretComment> selectSecretCommentBySecretId(PageBean pb) {
		// TODO Auto-generated method stub
		List<SecretComment> list = secretCommentMappere
				.selectSecretCommentBySecretId(pb);
		return list;
	}

	@Override
	public int secretCommentjiajian1(int total, int secretid) {
		// TODO Auto-generated method stub
		Secret s=secretMapper.selectByPrimaryKey(secretid);
		s.setCommentTotal(s.getCommentTotal()+total);
		return secretMapper.updateByPrimaryKeySelective(s);
	}

}
