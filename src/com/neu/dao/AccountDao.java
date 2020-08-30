package com.neu.dao;

import java.util.List;
import java.util.Map;

public interface AccountDao {
    /**
     * 添加账单
     * @param name
     * @param count
     * @param price
     * @param date
     * @param gross
     * @param user_id
     * @return
     */
    int addAccount(String name, String count, String price, String date,String gross,String user_id);

    /**
     * 修改账单
     * @param name
     * @param count
     * @param price
     * @param date
     * @param gross
     * @param user_id
     * @return
     */
    int updateAcountById(String name, String count, String price, String date,String gross,String user_id);

    /**
     * 查询所有账单
     * @param user_id
     * @return
     */
    List<Map<String,Object>> findGoodsAll(String user_id);

    /**
     * 按id查询账单
     * @param goods_id
     * @param goods_user_id
     * @return
     */
    Map<String,Object> findGoodsById(String goods_id, String goods_user_id);

    /**
     * 按名称查找账单
     * @param goods_name
     * @param user_id
     * @return
     */
    List<Map<String,Object>> findGoodsByName(String goods_name, String user_id);

    /**
     * 按数量查找账单
     * @param goods_count
     * @param user_id
     * @return
     */
    List<Map<String,Object>> findGoodsByCount(String goods_count, String user_id);

    /**
     * 按单价查找账单
     * @param goods_price
     * @param user_id
     * @return
     */
    List<Map<String,Object>> findGoodsByPrice(String goods_price, String user_id);

    /**
     * 按总价查找账单
     * @param goods_price
     * @param user_id
     * @return
     */
    List<Map<String,Object>> findGoodsByPriceTotal(String goods_price, String user_id);

    /**
     * 按日期查找账单
     * @param goods_date
     * @param user_id
     * @return
     */
    List<Map<String,Object>> findGoodsByDate(String goods_date, String user_id);

    /**
     * 按起止日期查找账单
     * @param goods_starDate
     * @param goods_nowDate
     * @param user_id
     * @return
     */
    List<Map<String,Object>> findGoodsByStarNow(String goods_starDate, String goods_nowDate, String user_id);

    /**
     * 按id删除商品
     * @param id
     * @param user_id
     * @return
     */
    int deleteAccountById(String id,String user_id);

    /**
     * 按名称删除商品
     * @param name
     * @param user_id
     * @return
     */
    int deleteAccountByName(String name,String user_id);

}
