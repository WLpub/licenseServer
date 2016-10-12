package controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import model.Product;
import service.ProductService;

@Controller
public class ProductController {
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Resource
	private ProductService productService;
	
	@RequestMapping(value = "/getProductByID", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody JSONObject getProductByID(@RequestBody Product product,HttpSession httpSession) {
		JSONObject ret = new JSONObject();
		try {
			if(httpSession.getAttribute("user")==null){
				throw new Exception("用户未登录！");
			}
			ret.put("product",productService.selectProductByID(product.getId()));
			ret.put("status", 1);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
			ret.put("status", -1);
			ret.put("errMsg", e.getMessage());
			return ret;
		}
		return ret;
	}
	@RequestMapping(value = "/getProducts", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody JSONObject getProducts() {
		JSONObject ret = new JSONObject();
		try {
			ret.put("products",productService.getProducts());
			ret.put("status", 1);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
			ret.put("status", -1);
			ret.put("errMsg", e.getMessage());
			return ret;
		}
		return ret;
	}
}
