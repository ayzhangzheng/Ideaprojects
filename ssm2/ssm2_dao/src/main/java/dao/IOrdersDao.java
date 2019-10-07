package dao;


import domain.Orders;

import domain.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrdersDao {

    //查询所有的订单信息
    @Select("select * from orders")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product",column = "productId",
                    javaType = Product.class,
                    one = @One(select = "dao.IProductDao.findById")),
    })
    public List<Orders> findAll() throws Exception;


    //根据id查找信息
    @Select("select * from orders where id=#{id}")
    @Results({
            @Result(id=true,column = "id",property = "id"),
            @Result(column = "orderNum",property = "orderNum"),
            @Result(column = "orderTime",property = "orderTime"),
            @Result(column = "peopleCount",property = "peopleCount"),
            @Result(column = "orderDesc",property = "orderDesc"),
            @Result(column = "payType",property = "payType"),
            @Result(column = "orderStatus",property = "orderStatus"),
            @Result(column = "productId",property = "product",one = @One(select = "dao.IProductDao.findById")),
            @Result(column = "id",property = "travellers",many = @Many(select = "dao.ITravellerDao.findByOrdersId")),
            @Result(column = "memberId",property = "member",one = @One(select = "dao.IMemberDao.findById")),
    })
    public Orders findById(int id) throws Exception;

}
