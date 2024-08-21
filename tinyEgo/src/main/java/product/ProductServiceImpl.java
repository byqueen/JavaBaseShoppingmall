package product;

import java.util.*;

public class ProductServiceImpl implements ProductService {
	ProductDao dao = null;
	ProductServiceImpl () {
		dao = new ProductDaoImpl();
	}

	@Override
	public List<HashMap<String, Object>> getSelectAllProd(ProductVo vo) {
		return dao.getSelectAllProd(vo);
	}

	@Override
	public HashMap<String, Object> getSelectOneProd(ProductVo vo) {
		return dao.getSelectOneProd(vo);
	}

	@Override
	public void insertProd(ProductVo vo) {
		dao.insertProd(vo);
	}

	@Override
	public void updateProd(ProductVo vo) {
		dao.updateProd(vo);
	}
	
	@Override
	public void updateProd_fileX(ProductVo vo) {
		dao.updateProd_fileX(vo);
	}

	@Override
	public void deleteProd(ProductVo vo) {
		dao.deleteProd(vo);
	}

	@Override
	public int maxPid(ProductVo vo) {
		return dao.maxPid(vo);
	}

}
