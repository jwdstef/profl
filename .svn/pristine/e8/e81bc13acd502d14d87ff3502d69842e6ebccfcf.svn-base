package com.radixdigit.base;

import java.util.List;



/**
 *BaseSqlMapper继承SqlMapper，对Mapper进行接口封装，提供常用的增删改查组件；
 * 可以对一些通用数据库操在这里作进行扩展
 * @author chengjun
 *@careDate 2014/11/3
 */
public interface BaseSqlMapper<T extends BaseEntity ,P extends Object>  extends SqlMapper{
	/**
	 * 删除
	 * @param uId
	 * @return
	 */
	int deleteByPrimaryKey(Integer uId);
	/**
	 * 普通增加不返回id
	 * @param record
	 * @return
	 */
    int insert(T record);
    /**
     * 增加成功后返回id
     * @param record
     * @return
     */
    int insertSelective(T record);
    /**
     * 根据主键id获取
     * @param uId
     * @return
     */
    T selectByPrimaryKey(P uId);
    /**
     * 更新后返回id
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(T record);
    /**
     * 普通更新不返回id
     * @param record
     * @return
     */
    int updateByPrimaryKey(T record);
    /**
     * 分頁查詢
     * @param page
     * @return
     */
    List<T> selectTAllByPage(Page<T> page);
	
}
