package dao;

import java.util.List;

import model.Product;

public interface ProductDao {
	public Product selectProductByID(Integer id);

	public List<Product> getProducts();
}
