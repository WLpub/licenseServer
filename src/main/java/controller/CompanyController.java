package controller;

import java.io.FileInputStream;
import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
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
	
	@RequestMapping(value="/mailCompany", method=RequestMethod.POST)
    public @ResponseBody JSONObject mailCompany(@RequestParam("file") MultipartFile file,HttpSession httpsession){
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
        	FileInputStream  in = new FileInputStream(fileService.getPath()+fileName);  
        	POIFSFileSystem fs = new POIFSFileSystem(in);  
        	Workbook book = new HSSFWorkbook(fs);  
        	String c = book.getSheetAt(0).getRow(1).getCell(1).getStringCellValue();
        	 System.out.println(c);
        	 
        	String sendUserName = "m13818566641_2@163.com"; 
            String sendPassword = "Oracle1234"; 
              
            Properties properties = new Properties(); 
            properties.setProperty("mail.smtp.auth", "true");//服务器需要认证 
            properties.setProperty("mail.transport.protocol", "smtp");//声明发送邮件使用的端口 
           
            Session session = Session.getInstance(properties); 
            session.setDebug(true);//同意在当前线程的控制台打印与服务器对话信息 
              
            Message message = new MimeMessage(session);//构建发送的信息 
            message.setText("您的验证码为：3313");//信息内容 
            message.setFrom(new InternetAddress(sendUserName));//发件人 
              
            Transport transport = session.getTransport(); 
            transport.connect("smtp.163.com", 25, sendUserName, sendPassword);//连接发件人使用发件的服务器 
            transport.sendMessage(message, new Address[]{new InternetAddress("15212010020@fudan.edu.cn")});//接受邮件 
            transport.close(); 
        	
    	}catch(Exception e){
    		ret.put("status", -1);
    		ret.put("errMsg", e.getMessage());
    	}
        return ret;
    }
}
