package controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

import model.Company;
import model.CompanyFilter;
import model.User;
import service.CompanyService;
import service.FileService;
import service.PermissionService;
import service.UserService;

@Controller
public class CompanyController {
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(CompanyController.class);

	@Resource
	private CompanyService companyService;
	@Resource
	private UserService userService;
	@Resource
	private PermissionService permissionService;
	@Resource
	private FileService fileService;
	
	@RequestMapping(value="/uploadCompany", method=RequestMethod.POST)
    public @ResponseBody JSONObject uploadCompany(@RequestParam("companyName") String companyName, @RequestParam("file") MultipartFile file,HttpSession httpsession){
    	JSONObject ret = new JSONObject();
    	User user = (User)httpsession.getAttribute("user");
    	try{
    		if(user==null){
    			throw new Exception("用户未登录！");
    		}
    		String fileName = fileService.keepFile(file);
        	if(fileName.equals("")){
        		throw new Exception("文件保存失败!");
			}
			Company company = new Company();
			company.setLicense(fileName);
			company.setName(companyName);
			company.setUserID(user.getId());
			int comId = companyService.createCompany(company);
			if (comId < 0) {
				throw new Exception("上传失败，请稍后再试！");
			}
			ret.put("status", comId);
			permissionService.setUserPermission(user.getPermission());
			user.setPermission(permissionService.setCompony(true));
			httpsession.setAttribute("user", user);
    	}catch(Exception e){
    		ret.put("status", -1);
    		ret.put("errMsg", e.getMessage());
    	}
        return ret;
    }
	
	@RequestMapping(value = "/createCompany", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody JSONObject createCompany(@RequestBody Company company,HttpSession httpSession) {
		JSONObject ret = new JSONObject();
		try {
			User ur = (User)httpSession.getAttribute("user");
			if(ur==null){
				throw new Exception("用户未登录！");
			}
			company.setUserID(ur.getId());
			int crRet = companyService.createCompany(company);
			if(crRet>0){
				ret.put("status", crRet);
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
	
	@RequestMapping(value = "/getCompany", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody JSONObject getCompany(@RequestBody CompanyFilter companyFilter,HttpSession httpSession) {
		JSONObject ret = new JSONObject();
		try {
			User ur = (User)httpSession.getAttribute("user");
			if(ur==null){
				throw new Exception("用户未登录！");
			}
			ret.put("companys",companyService.selectCompanyByUserID(ur.getId(),companyFilter.getStart()));
			ret.put("count",companyService.getTotalCount(ur.getId()));
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
	
	//company
	@RequestMapping(value = "/getAllCompany", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody JSONObject getAllCompany(@RequestBody CompanyFilter companyFilter,HttpSession httpSession) {
		JSONObject ret = new JSONObject();
		try {
			User ur = (User)httpSession.getAttribute("user");
			if(ur==null){
				throw new Exception("用户未登录！");
			}
			ret.put("companys",companyService.selectCompanyByUserID(ur.getId(),companyFilter.getStart()));
			ret.put("count",companyService.getTotalCount(ur.getId()));
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
