package thread;

import java.util.Map;  

public class ServiceThreadMap {  
    Map<String, Thread> serviceMap;  
  
    public ServiceThreadMap(Map<String, Thread> serviceMap) {  
        super();  
        this.serviceMap = serviceMap;  
    }  
  
    public Map<String, Thread> getServiceMap() {  
        return serviceMap;  
    }  
} 