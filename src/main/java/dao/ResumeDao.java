package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import model.Resume;

public interface ResumeDao {

	List<Resume> selectResumeByUserId(@Param("start")int start,@Param("user_id")int user_id);

	List<Resume> selectResumeById(int id);

	Integer getResumeCount(Integer id);
}
