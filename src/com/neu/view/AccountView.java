package com.neu.view;

import com.neu.dao.AccountDao;
import com.neu.dao.impl.AccountDaoImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.neu.util.Read.getIn;

public class AccountView extends SuperView {

    private static AccountDao accountDao = new AccountDaoImpl();

    /**
     * 添加账目
     */
    protected void addAccountMenu() {
        Date date = null;
        String user_id = userAll.get("user_id").toString();
        SimpleDateFormat mydate = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("请输入您购买的商品:");
        String name = getIn().next();
        System.out.println("请输入您购买的数量:");
        int count = getIn().nextInt();
        String countStr = String.valueOf(count);
        System.out.println("请输入您购买的该商品的单品金额:");
        double price = getIn().nextDouble();
        String priceStr = String.valueOf(price);
        System.out.println("请输入您购买商品的日期(yyyy-MM-dd):");
        String dateStr = getIn().next();
        double gross = count*price;
        String grossStr = String.valueOf(gross);
        System.out.println(count+"-"+countStr+"-"+price+"*"+priceStr+"*"+gross+"+"+grossStr);
        try {
            //把输入的日期字符串转成日期类型
            date = mydate.parse(dateStr);
            //把日期类型转成字符串方便后面与数据库的交互
            dateStr = mydate.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("是否确认添加?(1.确认\t2.取消)");
        int confirm = getIn().nextInt();
        switch (confirm){
            case 1:
                int i = accountDao.addAccount(name, countStr, priceStr, dateStr, grossStr, user_id);
                if (i>0){
                    System.out.println("添加成功!");
                }else {
                    System.out.println("添加失败!");
                }
                break;
            case 2:
                break;
            default:
                System.out.println("输入有误!");
                break;
        }
    }

    /**
     * 修改账目
     */
    protected void updateAccountMenu(){
        String user_id = userAll.get("user_id").toString();
        System.out.println("请输入需要修改的商品名:");
        Object goods_name = getIn().next();
        List<Map<String,Object>> goodsByName = accountDao.findGoodsByName(goods_name.toString(),user_id);
        if (goodsByName.size()>0){
            for(int i =0;i<goodsByName.size();i++){
                System.out.println("商品编号:"+goodsByName.get(i).get("goods_id").toString()+"\t"+"商品名称:"+goodsByName.get(i).get("goods_name").toString()+"\t"+
                        "商品数量:"+goodsByName.get(i).get("goods_count").toString()+"\t"+"商品单价:"+goodsByName.get(i).get("goods_price").toString()+"\t"+
                        "商品购买日期:"+goodsByName.get(i).get("goods_date").toString()+"\t"+"商品总价:"+goodsByName.get(i).get("goods_gross").toString());
            }
            System.out.println("请确认需要修改的商品编号:");
            Object goods_id = getIn().next();
            Map<String, Object> goodsById = accountDao.findGoodsById(goods_id.toString(), user_id);
            if (goodsById.size()>0){
                Date date = null;
                SimpleDateFormat mydate = new SimpleDateFormat("yyyy-MM-dd");
                System.out.println("请输入您要修改的数量:");
                int count = getIn().nextInt();
                String countStr = String.valueOf(count);
                System.out.println("请输入您要修改的该商品的单品金额:");
                double price = getIn().nextDouble();
                String priceStr = String.valueOf(price);
                System.out.println("请输入您要修改的购买商品的日期(yyyy-MM-dd):");
                String dateStr = getIn().next();
                double gross = count*price;
                String grossStr = String.valueOf(gross);
                System.out.println(count+"-"+countStr+"-"+price+"*"+priceStr+"*"+gross+"+"+grossStr);
                try {
                    //把输入的日期字符串转成日期类型
                    date = mydate.parse(dateStr);
                    //把日期类型转成字符串方便后面与数据库的交互
                    dateStr = mydate.format(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                System.out.println("是否确认修改?(1.确认\t2.取消)");
                int confirm = getIn().nextInt();
                switch (confirm){
                    case 1:
                        int row = accountDao.updateAcountById(countStr,priceStr,dateStr,grossStr,goods_id.toString(),user_id);
                        if (row > 0){
                            System.out.println("修改成功!");
                        }else {
                            System.out.println("修改失败!");
                        }
                        break;
                    case 2:
                        break;
                    default:
                        System.out.println("输入有误!");
                        break;
                }
            }
        }else {
            System.out.println("您要修改的商品不存在!");
        }
    }

    /**
     * 查询账目菜单
     */
    protected void selectAccountMenu(){
        String[] a = {"1.按商品编号查找","2.按商品名称查找","3.按数量查找","4.按价格查找","5.按日期查找","6.查询所有商品"};
        for (int i =0;i<a.length;i++){
            System.out.println(a[i]);
        }
        System.out.println("请输入您的选择:");
        int nextInt = getIn().nextInt();
        switch (nextInt){
            case 1:
                selectAccountById();
                break;
            case 2:
                selectAccountByName();
                break;
            case 3:
                selectAccountByCount();
                break;
            case 4:
                selectAccountByPriceMenu();
                break;
            case 5:
                selectAccountByDateMenu();
                break;
            case 6:
                selectAccountAll();
                default:
                    System.out.println("输入有误!");
                    break;
        }
    }

    /**
     * 删除账单菜单
     */
    protected void deleteAccountMenu() {
        selectAccountAll();
        System.out.println("1.按商品编号删除\t2.按商品名称删除");
        int next = getIn().nextInt();
        switch (next){
            case 1:
                deleteAccountById();
                break;
            case 2:
                deleteAccountByName();
                break;
                default:
                    System.out.println("输入有误!");
                    break;
        }
    }

    /**
     * 按商品编号查询
     */
    private void selectAccountById() {
        Map<String, Object> goodsById = null;
        String user_id = userAll.get("user_id").toString();
        System.out.println("请输入商品编号:");
        String id = getIn().next();
        goodsById = accountDao.findGoodsById(id, user_id);
        if (goodsById.size()>0){
            System.out.println("商品编号:"+goodsById.get("goods_id").toString()+"\t"+"商品名称:"+goodsById.get("goods_name").toString()+"\t"+
                    "商品数量:"+goodsById.get("goods_count").toString()+"\t"+"商品单价:"+goodsById.get("goods_price").toString()+"\t"+
                    "商品购买日期:"+goodsById.get("goods_date").toString()+"\t"+"商品总价:"+goodsById.get("goods_gross").toString());
        }else {
            System.out.println("未查询到编号为"+id+"的商品:");
        }
    }

    /**
     * 按商品名称查找
     */
    private void selectAccountByName(){
        String user_id = userAll.get("user_id").toString();
        System.out.println("请输入商品名称:");
        String name = getIn().next();
        List<Map<String, Object>> goodsByName = accountDao.findGoodsByName(name, user_id);
        if (goodsByName.size()>0){
            for(int i =0;i<goodsByName.size();i++){
                System.out.println("商品编号:"+goodsByName.get(i).get("goods_id").toString()+"\t"+"商品名称:"+goodsByName.get(i).get("goods_name").toString()+"\t"+
                        "商品数量:"+goodsByName.get(i).get("goods_count").toString()+"\t"+"商品单价:"+goodsByName.get(i).get("goods_price").toString()+"\t"+
                        "商品购买日期:"+goodsByName.get(i).get("goods_date").toString()+"\t"+"商品总价:"+goodsByName.get(i).get("goods_gross").toString());
            }
        }else {
            System.out.println("未查询到名为"+name+"的商品:");
        }
    }

    /**
     * 按商品数量查找
     */
    private void selectAccountByCount() {
        String user_id = userAll.get("user_id").toString();
        System.out.println("请输入商品数量:");
        String count = getIn().next();
        List<Map<String, Object>> goodsByCount = accountDao.findGoodsByCount(count, user_id);
        if (goodsByCount.size()>0){
            for(int i =0;i<goodsByCount.size();i++){
                System.out.println("商品编号:"+goodsByCount.get(i).get("goods_id").toString()+"\t"+"商品名称:"+goodsByCount.get(i).get("goods_name").toString()+"\t"+
                        "商品数量:"+goodsByCount.get(i).get("goods_count").toString()+"\t"+"商品单价:"+goodsByCount.get(i).get("goods_price").toString()+"\t"+
                        "商品购买日期:"+goodsByCount.get(i).get("goods_date").toString()+"\t"+"商品总价:"+goodsByCount.get(i).get("goods_gross").toString());
            }
        }else {
            System.out.println("未查询到数量为"+count+"的商品:");
        }
    }

    /**
     * 按商品价格查找菜单
     */
    private void selectAccountByPriceMenu() {
        System.out.println("1.按单品单价查找\t2.按单品总价查找");
        int next = getIn().nextInt();
        switch (next){
            case 1:
                selectAccountByPrice();
                break;
            case 2:
                selectAccountByPriceTotal();
                break;
            default:
                System.out.println("输入有误!");
                break;
        }
    }

    /**
     * 按商品价格查找
     */
    private void selectAccountByPrice() {
        String user_id = userAll.get("user_id").toString();
        System.out.println("请输入商品价格:");
        String price = getIn().next();
        List<Map<String, Object>> goodsByprice = accountDao.findGoodsByPrice(price, user_id);
        if (goodsByprice.size()>0){
            for(int i =0;i<goodsByprice.size();i++){
                System.out.println("商品编号:"+goodsByprice.get(i).get("goods_id").toString()+"\t"+"商品名称:"+goodsByprice.get(i).get("goods_name").toString()+"\t"+
                        "商品数量:"+goodsByprice.get(i).get("goods_count").toString()+"\t"+"商品单价:"+goodsByprice.get(i).get("goods_price").toString()+"\t"+
                        "商品购买日期:"+goodsByprice.get(i).get("goods_date").toString()+"\t"+"商品总价:"+goodsByprice.get(i).get("goods_gross").toString());
            }
        }else {
            System.out.println("未查询到单价为"+price+"的商品:");
        }
    }

    /**
     *按总价查询
     */
    private void selectAccountByPriceTotal() {
        String user_id = userAll.get("user_id").toString();
        System.out.println("请输入要查询的商品的总价:");
        String price = getIn().next();
        List<Map<String, Object>> goodsByprice = accountDao.findGoodsByPriceTotal(price, user_id);
        if (goodsByprice.size()>0){
            for(int i =0;i<goodsByprice.size();i++){
                System.out.println("商品编号:"+goodsByprice.get(i).get("goods_id").toString()+"\t"+"商品名称:"+goodsByprice.get(i).get("goods_name").toString()+"\t"+
                        "商品数量:"+goodsByprice.get(i).get("goods_count").toString()+"\t"+"商品单价:"+goodsByprice.get(i).get("goods_price").toString()+"\t"+
                        "商品购买日期:"+goodsByprice.get(i).get("goods_date").toString()+"\t"+"商品总价:"+goodsByprice.get(i).get("goods_gross").toString());
            }
        }else {
            System.out.println("未查询到总价为"+price+"的商品:");
        }
    }

    /**
     * 按商品的日期查找菜单
     */
    private void selectAccountByDateMenu(){
        System.out.println("1.按具体日期查找\t2.按起止日期查找\n请选择:");
        int next = getIn().nextInt();
        switch (next){
            case 1:
                selectAccountByDate();
                break;
            case 2:
                selectAccountByStatNow();
                break;
                default:
                    System.out.println("输入有误!");
                    break;
        }
    }

    /**
     * 按商品日期查询
     */
    private void selectAccountByDate() {
        Date parse = null;
        String date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String user_id = userAll.get("user_id").toString();
        System.out.println("请输入查找日期:");
        date = getIn().next();
        try {
            parse = sdf.parse(date);
            date = sdf.format(parse);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<Map<String, Object>> goodsByDate = accountDao.findGoodsByDate(date, user_id);
        if (goodsByDate.size()>0){
            for(int i =0;i<goodsByDate.size();i++){
                System.out.println("商品编号:"+goodsByDate.get(i).get("goods_id").toString()+"\t"+"商品名称:"+goodsByDate.get(i).get("goods_name").toString()+"\t"+
                        "商品数量:"+goodsByDate.get(i).get("goods_count").toString()+"\t"+"商品单价:"+goodsByDate.get(i).get("goods_price").toString()+"\t"+
                        "商品购买日期:"+goodsByDate.get(i).get("goods_date").toString()+"\t"+"商品总价:"+goodsByDate.get(i).get("goods_gross").toString());
            }
        }else {
            System.out.println("未查询到日期为:"+date+"的商品:");
        }
    }

    /**
     * 按起止日期查找
     */
    private void selectAccountByStatNow() {
        Date parse1 = null;Date parse2 = null;
        String stardate = null;String nowdate = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String user_id = userAll.get("user_id").toString();
        System.out.println("请输入起始日期:");
        String starDate = getIn().next();
        System.out.println("请输入截止日期:");
        String nowDate = getIn().next();
        try {
            parse1 = sdf.parse(starDate);
            parse2 = sdf.parse(nowDate);
            stardate = sdf.format(parse1);
            nowdate = sdf.format(parse2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<Map<String, Object>> goodsByDate = accountDao.findGoodsByStarNow(stardate, nowdate, user_id);
        if (goodsByDate.size()>0){
            for(int i =0;i<goodsByDate.size();i++){
                System.out.println("商品编号:"+goodsByDate.get(i).get("goods_id").toString()+"\t"+"商品名称:"+goodsByDate.get(i).get("goods_name").toString()+"\t"+
                        "商品数量:"+goodsByDate.get(i).get("goods_count").toString()+"\t"+"商品单价:"+goodsByDate.get(i).get("goods_price").toString()+"\t"+
                        "商品购买日期:"+goodsByDate.get(i).get("goods_date").toString()+"\t"+"商品总价:"+goodsByDate.get(i).get("goods_gross").toString());
            }
        }else {
            System.out.println("未查询到日期在"+starDate+"到"+nowDate+"之间的商品:");
        }
    }

    /**
     * 查询所有账单
     */
    private void selectAccountAll() {
        String user_id = userAll.get("user_id").toString();
        List<Map<String, Object>> goodsAll = accountDao.findGoodsAll(user_id);
        if (goodsAll.size()>0){
            for(int i =0;i<goodsAll.size();i++){
                System.out.println("商品编号:"+goodsAll.get(i).get("goods_id").toString()+"\t"+"商品名称:"+goodsAll.get(i).get("goods_name").toString()+"\t"+
                        "商品数量:"+goodsAll.get(i).get("goods_count").toString()+"\t"+"商品单价:"+goodsAll.get(i).get("goods_price").toString()+"\t"+
                        "商品购买日期:"+goodsAll.get(i).get("goods_date").toString()+"\t"+"商品总价:"+goodsAll.get(i).get("goods_gross").toString());
            }
        }else {
            System.out.println("未查询到商品:");
        }
    }

    /**
     * 按商品编号删除
     */
    private void deleteAccountById() {
        System.out.println("请输入您要删除的商品编号:");
        String id = getIn().next();
        String user_id = userAll.get("user_id").toString();
        Map<String, Object> goodsById = accountDao.findGoodsById(id,user_id);
        if (goodsById.size()>0){
            int i = accountDao.deleteAccountById(id, user_id);
            if (i>0){
                System.out.println("删除成功!");
            }else {
                System.out.println("删除失败!");
            }
        }else {
            System.out.println("未搜索到该商品!");
        }
    }

    /**
     * 按商品名称删除
     */
    private void deleteAccountByName() {
        System.out.println("请输入您要删除的商品名称:");
        String name = getIn().next();
        String user_id = userAll.get("user_id").toString();
        List<Map<String, Object>> goodsByName = accountDao.findGoodsByName(name, user_id);
        if (goodsByName.size()>0){
            int i = accountDao.deleteAccountByName(name, user_id);
            if (i>0){
                System.out.println("删除成功!");
            }else {
                System.out.println("删除失败!");
            }
        }else {
            System.out.println("未搜索到该商品!");
        }
    }
}
