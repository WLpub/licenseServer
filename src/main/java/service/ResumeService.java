package service;

import java.util.List;
import model.Resume;

public interface ResumeService {

	List<Resume> getResumeByUserId(int start,int hraccount_id);

	List<Resume> getResumeById(int id);

	Integer getResumeCount(Integer id);
}
