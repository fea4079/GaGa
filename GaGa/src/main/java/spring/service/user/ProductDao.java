package spring.service.user;

import java.util.List;

import spring.common.Search;
import spring.domain.Product;





/**
 * Servlet implementation class ProductDAO
 */

public interface ProductDao{
	
	public Product findProduct(int prodNo) throws Exception;
	
	public String getProdName(String prodName) throws Exception;
				
	
	public void insertProduct(Product product)throws Exception;
			
	
	public List<Product> getProductList(Search search) throws Exception;
	
	
	
	public void updateProduct(Product product) throws Exception;

	
	public int getTotalCount(Search search) throws Exception ;
	
 
//	public String makeCurrentPageSql(String sql , Search search);
		
}
