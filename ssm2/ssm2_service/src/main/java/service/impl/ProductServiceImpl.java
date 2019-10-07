package service.impl;

import dao.IProductDao;
import domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.IProductService;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductDao iProductDao;

    public List<Product> findAll() throws Exception {
        return iProductDao.findAll();
    }

    public void save(Product product) throws Exception {
        iProductDao.save(product);
    }


}
