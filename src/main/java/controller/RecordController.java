package controller;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import model.Price;
import model.Product;
import model.Record;
import model.RecordFilter;
import model.User;
import service.ProductService;
import service.RecordService;
import service.UserService;

@Controller
public class RecordController {
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(RecordController.class);

	@Resource
	private RecordService recordService;
	@Resource
	private ProductService productService;
	@Resource
	private UserService userService;
	
	private List<Product> products;
	
	@RequestMapping(value = "/createRecord", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody JSONObject createRecord(@RequestBody Record record,HttpSession httpSession) {
		JSONObject ret = new JSONObject();
		try {
			if(products==null){
				products = productService.getProducts();
			}
			User ur = (User)httpSession.getAttribute("user");
			if(ur==null){
				throw new Exception("用户未登录！");
			}
			Product proTemp = null;
			for(Product pro : products){
				if(pro.getId()==record.getProductID()){
					proTemp = pro;
				}
			}
			if(proTemp==null){
				throw new Exception("产品不存在，请查证！");
			}
			System.out.println(proTemp.getPrice());
			JSONArray price = JSON.parseObject(proTemp.getPrice()).getJSONArray("price");
			Iterator<Object> it = price.iterator();
			JSONObject pr = null;
			
			while (it.hasNext()) {
				pr = (JSONObject) it.next();
				if(pr.getInteger("id")==record.getPriceID()){
					break;
				}
			}
			if(pr==null){
				throw new Exception("请选择购买时长！");
			}
			Price priceTemp = JSON.parseObject(pr.toString(), Price.class);  
			if(ur.getBalance()<priceTemp.getMoney()){
				throw new Exception("余额不足，请充值后购买！");
			}
			if(ur.getPoint()<priceTemp.getPoint()){
				throw new Exception("积分不足，请兑换积分后再购买！");
			}	
			record.setUserID(ur.getId());
			int crRet = recordService.createRecord(record);
			if(crRet>0){
				ur.setBalance(ur.getBalance()-priceTemp.getMoney());
				ur.setPoint(ur.getPoint()-priceTemp.getPoint());
				ret.put("status",crRet);
				userService.updateUser(ur);
				httpSession.setAttribute("user", ur);
			}else{
				throw new Exception("购买失败,请稍后再试！");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
			ret.put("status", -1);
			ret.put("errMsg", e.getMessage());
			return ret;
		}
		return ret;
	}
	
	@RequestMapping(value = "/getRecord", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody JSONObject getRecord(@RequestBody RecordFilter recordFilter,HttpSession httpSession) {
		JSONObject ret = new JSONObject();
		try {
			User ur = (User)httpSession.getAttribute("user");
			if(ur==null){
				throw new Exception("用户未登录！");
			}
			ret.put("records",recordService.selectRecordByUserID(ur.getId(),recordFilter.getStart()));
			ret.put("count",recordService.getTotalCount(ur.getId()));
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
