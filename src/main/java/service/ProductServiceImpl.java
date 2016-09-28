package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ProductDao;
import model.Product;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired  
    private ProductDao productDao;

	@Override
	public Product selectProductByID(Integer id) {
		return productDao.selectProductByID(id);
	}  
  
}
