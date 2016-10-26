package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import model.License;

public interface LicenseDao {
	public License selectLicenseByID(Integer id);

	public List<License> selectLicenseByUserID(@Param("userID")Integer userID,@Param("start") Integer start);

	public int createLicense(License license);

	public List<License> selectLicenseByStatus(@Param("status")String status,@Param("start") Integer start);

	public List<License> selectLicenseByStatusUserID(@Param("userID")Integer userID,@Param("status") String status,@Param("start") Integer start);

	public int getTotalCountByUserID(Integer userID);
	
	public int getTotalCountByStatus(String status);
	
	public int getTotalCountByStatusUserID(@Param("userID")Integer userID,@Param("status") String status);
	
	public int getTotalCount();

	public void updateLicenseStatus(License license);
}
