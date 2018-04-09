package com.moyou.moyouRms.dao.nearbyrank;
import com.moyou.moyouRms.model.nearbyrank.NearbyRank;
import java.util.List;
import com.moyou.moyouRms.interceptor.Assist;
import org.apache.ibatis.annotations.Param;
public interface NearbyRankDao{
	/**
	 * 获得NearbyRank数据的总行数,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * @param assist
	 * @return
	 */
    long getNearbyRankRowCount(Assist assist);
	/**
	 * 获得NearbyRank数据集合,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * @param assist
	 * @return
	 */
    List<NearbyRank> selectNearbyRank(Assist assist);
	/**
	 * 获得一个NearbyRank对象,以参数NearbyRank对象中不为空的属性作为条件进行查询
	 * @param obj
	 * @return
	 */
    NearbyRank selectNearbyRankByObj(NearbyRank obj);
	/**
	 * 通过NearbyRank的id获得NearbyRank对象
	 * @param id
	 * @return
	 */
    NearbyRank selectNearbyRankById(String id);
	/**
	 * 插入NearbyRank到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertNearbyRank(NearbyRank value);
	/**
	 * 插入NearbyRank中属性值不为null的数据到数据库
	 * @param value
	 * @return
	 */
    int insertNonEmptyNearbyRank(NearbyRank value);
	/**
	 * 通过NearbyRank的id删除NearbyRank
	 * @param id
	 * @return
	 */
    int deleteNearbyRankById(String id);
	/**
	 * 通过辅助工具Assist的条件删除NearbyRank
	 * @param assist
	 * @return
	 */
    int deleteNearbyRank(Assist assist);
	/**
	 * 通过NearbyRank的id更新NearbyRank中的数据,包括null值
	 * @param enti
	 * @return
	 */
    int updateNearbyRankById(NearbyRank enti);
 	/**
	 * 通过辅助工具Assist的条件更新NearbyRank中的数据,包括null值
	 * @param value
	 * @param assist
	 * @return
	 */
    int updateNearbyRank(@Param("enti") NearbyRank value, @Param("assist") Assist assist);
	/**
	 * 通过NearbyRank的id更新NearbyRank中属性不为null的数据
	 * @param enti
	 * @return
	 */
    int updateNonEmptyNearbyRankById(NearbyRank enti);
 	/**
	 * 通过辅助工具Assist的条件更新NearbyRank中属性不为null的数据
	 * @param value
	 * @param assist
	 * @return
	 */
    int updateNonEmptyNearbyRank(@Param("enti") NearbyRank value, @Param("assist") Assist assist);
}