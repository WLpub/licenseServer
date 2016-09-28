package dao;

import model.Product;

public interface ProductDao {
	public Product selectProductByID(Integer id);
}
