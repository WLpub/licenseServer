package service;

import java.util.List;

import model.Product;

public interface ProductService {
	Product selectProductByID(Integer id);

	List<Product> getProducts();
}
