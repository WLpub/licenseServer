package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.CompanyDao;
import model.Company;

@Service
public class CompanyServiceImpl implements CompanyService {
	@Autowired  
    private CompanyDao companyDao;  
  
	@Override
    public Company selectCompanyByID(Integer companyID) {  
        return companyDao.selectCompanyByID(companyID);  
          
    }

    @Override
	public int createCompany(Company company) {
    	companyDao.createCompany(company);
    	return company.getId();
	}

	@Override
	public List<Company> selectCompanyByUserID(Integer userID,Integer start) {  
		return companyDao.selectCompanyByUserID(userID,start);  
    }

	@Override
	public int getTotalCount(Integer userID) {
		return companyDao.getTotalCount(userID);
	}

}
