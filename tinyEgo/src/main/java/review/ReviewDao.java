package review;

import java.util.HashMap;
import java.util.List;

public interface ReviewDao {
	List<HashMap<String, Object>> getSelectAllReview (ReviewVo vo);
	HashMap<String, Object> getSelectOneReview (ReviewVo vo);
	void insertReview (ReviewVo vo);
	int totalRecord (ReviewVo vo);
}
