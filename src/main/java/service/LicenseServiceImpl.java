package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.LicenseDao;
import model.License;

@Service
public class LicenseServiceImpl implements LicenseService {
	@Autowired  
    private LicenseDao licenseDao;  
  
	@Override
	public License selectLicenseByID(Integer id) {
		return licenseDao.selectLicenseByID(id);
	}

	@Override
	public List<License> selectLicenseByUserID(Integer userID, Integer start) {
		return licenseDao.selectLicenseByUserID(userID, start);
	}

	@Override
	public int createLicense(License license) {
		return licenseDao.createLicense(license);
	}

	@Override
	public List<License> selectLicenseByStatus(String status, Integer start) {
		return licenseDao.selectLicenseByStatus(status,start);
	}

	@Override
	public List<License> selectLicenseByStatusUserID(Integer userID, String status, Integer start) {
		return licenseDao.selectLicenseByStatusUserID(userID,status,start);
	}

	@Override
	public int getTotalCountByUserID(Integer userID) {
		return licenseDao.getTotalCountByUserID(userID);
	}

	@Override
	public int getTotalCount() {
		return licenseDao.getTotalCount();
	}

	@Override
	public int getTotalCountByStatus(String status) {
		return licenseDao.getTotalCountByStatus(status);
	}

	@Override
	public int getTotalCountByStatusUserID(String status, Integer userID) {
		return licenseDao.getTotalCountByStatusUserID(userID,status);
	}
}
