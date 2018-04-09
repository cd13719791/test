package com.moyou.moyouRms.service.secret.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moyou.moyouRms.dao.anonymousAvatar.AnonymousAvatarMapper;
import com.moyou.moyouRms.dao.secret.SecretMapper;
import com.moyou.moyouRms.dao.secretRobot.SecretRobotMapper;
import com.moyou.moyouRms.dao.user.UserCountMapper;
import com.moyou.moyouRms.dao.user.UserInfoMapper;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.secret.Secret;
import com.moyou.moyouRms.model.secret.SecretComment;
import com.moyou.moyouRms.model.secret.SecretContent;
import com.moyou.moyouRms.model.secret.SecretContentInsertModel;
import com.moyou.moyouRms.model.secret.SecretInsertModel;
import com.moyou.moyouRms.model.user.UserInfo;
import com.moyou.moyouRms.service.secret.SecretCommentService;
import com.moyou.moyouRms.service.secret.SecretContentService;
import com.moyou.moyouRms.service.secret.SecretService;
import com.moyou.moyouRms.service.user.UserInfoService;
import com.moyou.moyouRms.service.user.UserService;
@Service
public class SecretServiceImpl implements SecretService {
@Resource
private SecretMapper secretMapper;
@Resource
private UserInfoService userInfoService;
@Resource
private SecretContentService secretContentService;
@Resource
private SecretCommentService secretCommentService;
@Resource
private AnonymousAvatarMapper anonymousAvatarMapper;
@Resource
private UserCountMapper userCountMapper;
@Resource
private UserInfoMapper userInfoMapper;
@Resource
private UserService userService;
@Resource
private SecretRobotMapper secretRobotMapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return secretMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Secret record) {
		// TODO Auto-generated method stub
		return secretMapper.insert(record);
	}

	@Override
	public int insertSelective(Secret record) {
		// TODO Auto-generated method stub
		return secretMapper.insertSelective(record);
	}

	@Override
	public Secret selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return secretMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Secret record) {
		return secretMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Secret record) {
		// TODO Auto-generated method stub
		return secretMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Secret> selectSecretByPageBeanParam(PageBean pb) {
		// TODO Auto-generated method stub
		return secretMapper.selectSecretByPageBeanParam(pb);
	}

	@Override
	public Secret selectSecretDetail(PageBean record) {
		// TODO Auto-generated method stub
		Integer secretId=Integer.valueOf(record.getConditions().get("secretId").toString());
		Secret s=secretMapper.selectSecretDetail(secretId);
		s.setCreaterUser(userInfoService.selectUsreNickNameAndAvatar(s.getUserId()));
		s.setSecretContentList(secretContentService.selectSecretContentBySecretId(secretId));//内容集合
		return s;
	}

	@Override
	public Map<String, Object> selectSecretCount() {
		// TODO Auto-generated method stub
		return secretMapper.selectSecretCount();
	}

	@Override
	public Secret selectH5SecretDetail(PageBean record) {
		// TODO Auto-generated method stub
		Integer secretId=Integer.valueOf(record.getConditions().get("secretId").toString());
		Secret s=secretMapper.selectSecretDetail(secretId);
		UserInfo userInfo=new UserInfo();
		userInfo.setAvatar(anonymousAvatarMapper.selectByPrimaryKey(s.getAnonymousAvatarId()).getUrl());
		s.setCreaterUser(userInfo);
		List<SecretContent> list=new ArrayList<SecretContent>();
		list=secretContentService.selectSecretContentBySecretId(secretId);
		s.setSecretContentList(list);//内容集合
		return s;
	}

	@Override
	public Integer updateUserCountSecretJian1(Integer userId) {
		return userCountMapper.updateUserCountSecretJian1(userId);
	}

	/**
	 * insert Secret
	 * 
	 * @param insertModel
	 * @return
	 */
	@Override
	public Integer insertSecret(SecretInsertModel insertModel) {
	Integer userId = null;
		Integer gender = insertModel.getGender();
		if (gender == 1) {
			List<UserInfo> userInfo = userInfoMapper.querySexWoman();
			if (userInfo == null) {
				userInfo = new ArrayList<UserInfo>();
			}
			Collections.shuffle(userInfo);// 随机集合
			userId = userInfo.get(0).getUserId();
		} else {
			List<UserInfo> userInfo = userInfoMapper.queryMan();
			if (userInfo == null) {
				userInfo = new ArrayList<UserInfo>();
			}
			Collections.shuffle(userInfo);// 随机集合
			userId = userInfo.get(0).getUserId();
		}
		insertModel.setUserId(userId);
		ArrayList<SecretContentInsertModel> contents = insertModel.getContents();
		Function<Integer, SecretContentInsertModel> findFirst = flag -> contents.parallelStream()
				.filter(item -> item.getContentType() == flag).findFirst().get();
		contents.sort(Comparator.comparingInt(SecretContentInsertModel::getSorting));
		if((Integer.valueOf((int) insertModel.getContents().stream().filter(item -> item.getContentType()==1).count()))>0){//添加的图片数量 大于0
		// 0表示文字 1表示图片
		insertModel.setFirstImage(findFirst.apply(1).getTextOrPicture());
		insertModel.setExtendData(findFirst.apply(1).getExtendData());
		}
		insertModel.setImageTotal((int) contents.parallelStream().filter(item -> item.getContentType() == 1).count());
		insertModel.setFirstContent(findFirst.apply(0).getTextOrPicture());
		insertModel.setAnonymousAvatarId(secretMapper.queryAnonymousAvatarId());
		secretMapper.insertSecret(insertModel);
		final Integer sercretId = insertModel.getSecretId();
		contents.parallelStream().peek(content -> content.setSecretId(sercretId))
				.filter(content -> content.getContentType() == 1)
				.forEach(content -> content.setTextOrPicture( content.getTextOrPicture()));
		secretMapper.insertSecretContentList(contents);
		return sercretId;
	}

	@Override
	public int insertComment(SecretComment secretComment) {
		// TODO Auto-generated method stub
		secretComment.setCreateTime(new Date());
		Integer userId = userService.queryFakeUserList().getUserId();
		secretComment.setUserId(userId);
		secretCommentService.insertSelective(secretComment);
		return secretMapper.updateCommentTotalJIA1(secretComment.getSecretId());
	}

	@Override
	public int updateSecretReadCount(Secret secret) {
		// TODO Auto-generated method stub
		return secretMapper.updateSecretReadCount(secret);
	}

}
