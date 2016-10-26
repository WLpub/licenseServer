package service;

import java.util.List;

import model.Company;

public interface CompanyService {
	Company selectCompanyByID(Integer id);

	List<Company> selectCompanyByUserID(Integer userID,Integer start);

	int createCompany(Company company);

	int getTotalCount(Integer userID);

	List<Company> selectCompanyByStatus(Integer start,String status);

	int getCountByStatus(String status);

	void updateCompanyStatus(Company company,String permission);

}
