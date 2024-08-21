package user;

import java.util.HashMap;
import java.util.List;

public class UserServiceImpl implements UserService {
	UserDao dao = null;
	public UserServiceImpl() {
		dao = new UserDaoImpl();
	}
	
	@Override
	public List<HashMap<String, Object>> getSelectAllUser(UserVo vo) {
		return dao.getSelectAllUser(vo);
	}
	
	@Override
	public HashMap<String, Object> getSelectOneUser(UserVo vo) {
		return dao.getSelectOneUser(vo);
	}
	
	@Override
	public void insertUser(UserVo vo) {
		dao.insertUser(vo);
	}
	
	@Override
	public void updateUser(UserVo vo) {
		dao.updateUser(vo);
	}
	
	@Override
	public void updateUser_pwdX(UserVo vo) {
		dao.updateUser_pwdX(vo);
	}
	
	@Override
	public void deleteUser(UserVo vo) {
		dao.deleteUser(vo);
	}
	
	@Override
	public int totalRecord(UserVo vo) {
		return dao.totalRecord(vo);
	}
	
	@Override
	public HashMap<String, Object> userLogin(UserVo vo) {
		return dao.userLogin(vo);
	}

	@Override
	public String userIdCk(UserVo vo) {
		return dao.userIdCk(vo);
	}

}
