package thread;

import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class BootStrapper extends HttpServlet{  
  
    private static final long serialVersionUID = 1L;  
    private WebApplicationContext webApp;  
    private ServiceThreadMap serviceThreadMap;  
      
    /* (non-Javadoc) 
     * @see javax.servlet.GenericServlet#destroy() 
     */  
    @SuppressWarnings("deprecation")  
    @Override  
    public void destroy() {  
        // TODO Auto-generated method stub  
        Iterator<Thread> service = serviceThreadMap.getServiceMap().values().iterator();  
        while(service.hasNext()){  
            service.next().stop();  
        }  
    }  
  
    /* (non-Javadoc) 
     * @see javax.servlet.GenericServlet#init() 
     */  
    @Override  
    public void init() throws ServletException {  
        // TODO Auto-generated method stub  
        //super.init();  
        //得到WebApplicationContext對象  
        webApp = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());  
          
        // start send message thread  
        serviceThreadMap = (ServiceThreadMap) webApp.getBean("serviceMap");//得到spring-application.xml中註冊的線程列表  
        Iterator<Thread> service = serviceThreadMap.getServiceMap().values().iterator();//得到線程列表中的線程迭代器  
        /* 
         * 啟動列表中的每一個線程 
         */  
        while(service.hasNext()){  
            System.out.println("---------------------------------------------------");  
            service.next().start();  
        }  
    }  
  
      
}  
