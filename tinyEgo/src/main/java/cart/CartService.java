package cart;

import java.util.HashMap;
import java.util.List;

public interface CartService {
	List<HashMap<String, Object>> getSelectAllCart (CartVo vo);
	void insertCart (CartVo vo);
	void insertCartIn (CartVo vo);
	void updateCart (CartVo vo);
	void deleteCart (CartVo vo);
	void deleteCartAll (CartVo vo);
	
	void insertOrder (CartVo vo);
	List<HashMap<String, Object>> getSelectAllOrderAdm (CartVo vo);
	List<HashMap<String, Object>> getSelectAllOrderCust (CartVo vo);
	List<HashMap<String, Object>> getSelectOneOrder (CartVo vo);

}
