package product;

import java.util.*;

public interface ProductDao {
	List<HashMap<String, Object>> getSelectAllProd(ProductVo vo);
	HashMap<String, Object> getSelectOneProd(ProductVo vo);
	void insertProd(ProductVo vo);
	void updateProd(ProductVo vo);
	void updateProd_fileX(ProductVo vo);
	void deleteProd(ProductVo vo);
	int maxPid(ProductVo vo);
}
