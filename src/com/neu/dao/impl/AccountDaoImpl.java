package com.neu.dao.impl;

import com.neu.dao.AccountDao;
import com.neu.dao.BaseDao;
import java.util.List;
import java.util.Map;

public class AccountDaoImpl extends BaseDao implements AccountDao {

    /**
     * 添加账目
     * 需要获取到用户的id进行匹配
     * @param name
     * @param count
     * @param price
     * @param date
     * @return
     */
    @Override
    public int addAccount(String name, String count, String price, String date,String gross,String user_id) {
        int row = 0;
        String sql = "insert into tbl_account(goods_name,goods_count,goods_price,goods_date,goods_gross,goods_user_id) values(?,?,?,?,?,?)";
        row = executeUpdate(sql,name,count,price,date,gross,user_id);
        return row;
    }

    /**
     * 按商品编号修改账目
     * 需要获取到用户id和商品编号
     * @param count
     * @param price
     * @param date
     * @param gross
     * @param id
     * @param user_id
     * @return
     */
    @Override
    public int updateAcountById(String count, String price, String date, String gross, String id, String user_id) {
        int row = 0;
        String sql = "update tbl_account set goods_count = ?,goods_price = ?,goods_date = ?,goods_gross = ? where goods_id = ? and goods_user_id = ?";
        row = executeUpdate(sql,count,price,date,gross,id,user_id);
        return row;
    }

    /**
     * 查询自己拥有的所有商品
     * @param user_id
     * @return
     */
    @Override
    public List<Map<String, Object>> findGoodsAll(String user_id) {
        List<Map<String, Object>> list = null;
        String sql = "select * from tbl_account where goods_user_id = ?";
        list = executeQuery(sql,user_id);
        return list;
    }

    /**
     * 按商品编号查询
     * @param goods_id
     * @param user_id
     * @return
     */
    @Override
    public Map<String, Object> findGoodsById(String goods_id, String user_id) {
        List<Map<String,Object>> list = null;
        Map<String,Object> map = null;
        String sql = "select * from tbl_account where goods_id = ? and goods_user_id = ?";
        list = executeQuery(sql,goods_id,user_id);
        if (list.size()>0){
            map = list.get(0);
        }
        return map;
    }

    /**
     * 按商品名称查询
     * 需要获取到用户的id
     * @param goods_name
     * @param user_id
     * @return
     */
    @Override
    public List<Map<String,Object>> findGoodsByName(String goods_name,String user_id) {
        List<Map<String,Object>> list = null;
        String sql = "select * from tbl_account where goods_name = ? and goods_user_id = ?";
        list = executeQuery(sql,goods_name,user_id);
        return list;
    }

    /**
     * 按商品数量查找
     * @param goods_count
     * @param user_id
     * @return
     */
    @Override
    public List<Map<String, Object>> findGoodsByCount(String goods_count, String user_id) {
        List<Map<String, Object>> list = null;
        String sql = "select * from tbl_account where goods_count = ? and goods_user_id = ?";
        list = executeQuery(sql,goods_count,user_id);
        return list;
    }

    /**
     * 按商品价格查找
     * @param goods_price
     * @param user_id
     * @return
     */
    @Override
    public List<Map<String, Object>> findGoodsByPrice(String goods_price, String user_id) {
        List<Map<String, Object>> list = null;
        String sql = "select * from tbl_account where goods_price = ? and goods_user_id = ?";
        list = executeQuery(sql,goods_price,user_id);
        return list;
    }

    /**
     * 按商品总价查找
     * @param goods_gross
     * @param user_id
     * @return
     */
    @Override
    public List<Map<String, Object>> findGoodsByPriceTotal(String goods_gross, String user_id) {
        List<Map<String, Object>> list = null;
        String sql = "select * from tbl_account where goods_gross = ? and goods_user_id = ?";
        list = executeQuery(sql,goods_gross,user_id);
        return list;
    }

    /**
     * 按日期查找
     * @param goods_date
     * @param user_id
     * @return
     */
    @Override
    public List<Map<String, Object>> findGoodsByDate(String goods_date, String user_id) {
        List<Map<String, Object>> list = null;
        String sql = "select * from tbl_account where goods_date = ? and goods_user_id = ?";
        list = executeQuery(sql,goods_date,user_id);
        return list;
    }

    /**
     * 按起止日期查找
     * @param goods_starDate
     * @param goods_nowDate
     * @param user_id
     * @return
     */
    @Override
    public List<Map<String, Object>> findGoodsByStarNow(String goods_starDate, String goods_nowDate, String user_id) {
        List<Map<String, Object>> list = null;
        String sql = "select * from tbl_account where goods_date between ? and ? and goods_user_id = ?";
        list = executeQuery(sql,goods_starDate,goods_nowDate,user_id);
        return list;
    }

    /**
     * 按id删除
     * @param id
     * @param user_id
     * @return
     */
    @Override
    public int deleteAccountById(String id, String user_id) {
        int row = 0;
        String sql = "delete from tbl_account where goods_id = ? and goods_user_id = ?";
        row = executeUpdate(sql,id,user_id);
        return row;
    }

    /**
     * 按商品名称删除
     * @param name
     * @param user_id
     * @return
     */
    @Override
    public int deleteAccountByName(String name, String user_id) {
        int row = 0;
        String sql = "delete from tbl_account where goods_name = ? and goods_user_id = ?";
        row = executeUpdate(sql,name,user_id);
        return row;
    }
}
