package util;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import model.Product;
import service.ProductService; 

/*
 * ProductCache 单例模式确保每次只要一个实例
 * 利用updateProductsCache刷新
 */
public class ProductCache {
	private static ProductCache instance = new ProductCache();
	
	@Resource
	private ProductService productService;
	private List<Product> products = productService.getProducts();
	
	private ProductCache (){}

	public static ProductCache getInstance() {
		return instance;
	}
	
	public List<Product> getProducts(){
		return products;
	}
	
	public List<Product> updateProductsCache(){
		products = productService.getProducts();
		return products; 
	}
}
