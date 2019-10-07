package service.impl;

import com.github.pagehelper.PageHelper;
import dao.IOrdersDao;
import domain.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.IOrdersService;

import java.util.List;

@Service
@Transactional
public class OrdersServiceImpl implements IOrdersService {

    @Autowired
    private IOrdersDao ordersDao;

    public List<Orders> findAll(int page,int size) throws Exception {
        PageHelper.startPage(page, size);
        return ordersDao.findAll();
    }

    public Orders findById(int id) throws Exception {
        return ordersDao.findById(id);
    }


}
