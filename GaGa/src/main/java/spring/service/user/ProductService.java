package spring.service.user;

import java.util.Map;

import org.springframework.stereotype.Service;

import spring.common.Search;
import spring.domain.Product;



@Service
public interface ProductService {
	
	public void addProduct(Product product) throws Exception;
	
	public Product getProduct(int prodNo) throws Exception;
	
	public Map<String, Object> getProductList(Search search) throws Exception;
	
	public void updateProduct(Product product) throws Exception;
	
	public String getProdName(String prodName) throws Exception;
}