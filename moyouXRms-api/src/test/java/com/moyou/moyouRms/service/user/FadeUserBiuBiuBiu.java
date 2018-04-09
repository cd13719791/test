//package com.moyou.moyouRms.service.user;
//
//import java.util.List;
//
//import javax.annotation.Resource;
//
//import org.junit.Test;
//import org.springframework.dao.InvalidDataAccessApiUsageException;
//import org.springframework.data.geo.Point;
//import org.springframework.data.redis.core.GeoOperations;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//import org.springframework.test.annotation.Rollback;
//
//import com.hankcs.hanlp.dictionary.py.Pinyin;
//import com.hankcs.hanlp.dictionary.py.PinyinDictionary;
//import com.moyou.moyouRms.constants.CONSTANT;
//import com.moyou.moyouRms.model.user.UserInfo;
//import com.moyou.moyouRms.service.BaseJunit4;
//import com.moyou.moyouRms.util.StringUtil;
//
///**
// * @author created by Chenxv
// * @date 2017年9月4日 上午11:17:36
// */
//public class FadeUserBiuBiuBiu extends BaseJunit4 {
//	@Resource
//	private UserService userService;
//	@Resource(name = "redisTemplate_KVString")
//	private RedisTemplate<String, String> redisTemplate_KVString;
//	private StringRedisSerializer serializer = new StringRedisSerializer();
//
//	@Test
//	@Rollback(false)
//	public void insert() {
//		List<UserInfo> list = userService.selectFakeUserForBiuBiuBiu();
//		System.out.println(list.get(0).getCity().replace(" ", ""));
//		int index = 0;
//		for (int i = 0; i < list.size(); i++) {
//			UserInfo s = list.get(i);
//			if (s == null || s.getCity() == null) {
//				continue;
//			}
//			if (s.getCity().indexOf("市") > 0) {
//				s.setCity(s.getCity().split("市")[0]);
//			}
//			List<Pinyin> pinyinList = PinyinDictionary.convertToPinyin(s.getCity());
//			String pinYi = "";
//			for (Pinyin py : pinyinList) {
//				pinYi += py.getPinyinWithoutTone();
//			}
//			s.setCity(pinYi);
//			String nearbyKey = CONSTANT.MOMENT_TIME + s.getCity();// key
//			GeoOperations<String, String> geoOps = redisTemplate_KVString.opsForGeo();
//			// 用户新的坐标存储到redis
//			Integer currentUserId = s.getUserId();
//			try {
//				if (StringUtil.isNotEmpty(currentUserId) && s.getLatitude() > 0
//						&& s.getLongitude() > 0) {
//					redisTemplate_KVString.setKeySerializer(serializer);
//					redisTemplate_KVString.setValueSerializer(serializer);
//					geoOps.geoAdd(nearbyKey, new Point(s.getLongitude(), s.getLatitude()),
//							currentUserId + "");
//				}
//			} catch (InvalidDataAccessApiUsageException e) {
//				System.err.println(++index);
//			}
//			System.out.println(i);
//		}
//	}
// }
