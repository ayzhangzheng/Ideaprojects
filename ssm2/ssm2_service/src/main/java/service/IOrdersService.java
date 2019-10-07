package service;



import domain.Orders;


import java.util.List;

public interface IOrdersService {


    public List<Orders> findAll(int page,int size) throws Exception;

    public Orders findById(int id) throws Exception;

}
