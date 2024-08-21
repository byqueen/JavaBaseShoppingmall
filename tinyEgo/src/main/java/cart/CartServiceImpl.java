package cart;

import java.util.HashMap;
import java.util.List;

public class CartServiceImpl implements CartService {
	CartDao dao = null;
	CartServiceImpl() {
		dao = new CartDaoImpl();
	}
		
	@Override
	public List<HashMap<String, Object>> getSelectAllCart(CartVo vo) {
		return dao.getSelectAllCart(vo);
	}

	@Override
	public void insertCart(CartVo vo) {
		dao.insertCart(vo);
	}

	@Override
	public void insertCartIn(CartVo vo) {
		dao.insertCartIn(vo);
	}
	
	@Override
	public void updateCart(CartVo vo) {
		dao.updateCart(vo);
	}

	@Override
	public void deleteCart(CartVo vo) {
		dao.deleteCart(vo);
	}

	@Override
	public void deleteCartAll(CartVo vo) {
		dao.deleteCartAll(vo);
	}

	@Override
	public void insertOrder(CartVo vo) {
		dao.insertOrder(vo);
	}
	
	@Override
	public List<HashMap<String, Object>> getSelectAllOrderCust(CartVo vo) {
		return dao.getSelectAllOrderCust(vo);
	}
	
	@Override
	public List<HashMap<String, Object>> getSelectAllOrderAdm(CartVo vo) {
		return dao.getSelectAllOrderAdm(vo);
	}

	@Override
	public List<HashMap<String, Object>> getSelectOneOrder(CartVo vo) {
		return dao.getSelectOneOrder(vo);
	}


}
