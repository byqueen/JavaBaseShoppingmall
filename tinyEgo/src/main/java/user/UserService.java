package user;

import java.util.*;

public interface UserService {
	List<HashMap<String, Object>> getSelectAllUser (UserVo vo);
	HashMap<String, Object> getSelectOneUser (UserVo vo);
	void insertUser (UserVo vo);
	void updateUser (UserVo vo);
	void updateUser_pwdX (UserVo vo);
	void deleteUser (UserVo vo);
	int totalRecord (UserVo vo);
	HashMap<String, Object> userLogin (UserVo vo);
	String userIdCk (UserVo vo);
}
