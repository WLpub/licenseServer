package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ResumeDao;
import model.Resume;

@Service
public class ResumeServiceImpl implements ResumeService {
	@Autowired  
    private ResumeDao resumeDao;

	@Override
	public List<Resume> getResumeByUserId(int start,int user_id) {
		return resumeDao.selectResumeByUserId(start,user_id);
	}

	@Override
	public List<Resume> getResumeById(int id) {
		return resumeDao.selectResumeById(id);
	}

	@Override
	public Integer getResumeCount(Integer id) {
		// TODO Auto-generated method stub
		return resumeDao.getResumeCount(id);
	}
  
}
