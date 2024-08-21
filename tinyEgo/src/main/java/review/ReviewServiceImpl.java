package review;

import java.util.HashMap;
import java.util.List;

public class ReviewServiceImpl implements ReviewService {
	ReviewDao dao = null;
	ReviewServiceImpl () {
		dao = new ReviewDaoImpl();
	}
	
	@Override
	public List<HashMap<String, Object>> getSelectAllReview(ReviewVo vo) {
		return dao.getSelectAllReview(vo);
	}
	
	@Override
	public HashMap<String, Object> getSelectOneReview(ReviewVo vo) {
		return dao.getSelectOneReview(vo);
	}
	
	@Override
	public void insertReview(ReviewVo vo) {
		dao.insertReview(vo);
	}
	
	@Override
	public int totalRecord(ReviewVo vo) {
		return dao.totalRecord(vo);
	}
	
}
