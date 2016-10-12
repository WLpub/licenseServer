package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import model.Company;

public interface CompanyDao {
	public Company selectCompanyByID(Integer id);

	public int createCompany(Company company);
	
	public List<Company> selectCompanyByUserID(@Param("userID")Integer userID,@Param("start")Integer start);

	public int getTotalCount(Integer userID);
}
