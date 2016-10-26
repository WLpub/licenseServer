package service;

import java.util.List;

import model.License;

public interface LicenseService {
	License selectLicenseByID(Integer id);

	List<License> selectLicenseByUserID(Integer userID,Integer start);

	int createLicense(License license);

	int getTotalCount();
	
	int getTotalCountByUserID(Integer userID);
	
	int getTotalCountByStatus(String status);
	
	int getTotalCountByStatusUserID(String status,Integer userID);
	
	List<License> selectLicenseByStatus(String status,Integer start);
	
	List<License> selectLicenseByStatusUserID(Integer userID,String status,Integer start);
}
