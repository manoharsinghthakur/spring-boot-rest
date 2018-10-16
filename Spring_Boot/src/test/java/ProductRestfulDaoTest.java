import java.util.ArrayList;
import java.util.List;

import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.restful.dao.ProductRestfulDao;
import com.restful.model.Product;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;

@RunWith(MockitoJUnitRunner.class)
public class ProductRestfulDaoTest {

	@InjectMocks
	ProductRestfulDao productRestfulDao;
	
	@Mock
	EntityManager entityManager;
	
	@Mock
	Product product;
	
	@Mock
	TypedQuery<Product> query;
	
	@Test
	public void shouldAddproductReturnProductObject() {
		Mockito.doNothing().when(entityManager).persist(product);
		
		Assert.assertEquals(product, productRestfulDao.addproduct(product));
		Mockito.verify(entityManager).persist(product);
	}

	@Test
	public void shouldfindByIdReturnProductObject() {
		when(entityManager.find(Product.class,1)).thenReturn(product);
		
		productRestfulDao.findById(1);
		Mockito.verify(entityManager).find(Product.class,1);
	}
	
	@Test
	public void shouldupdateProductByIdReturnProductObject() {
		
		Assert.assertNotNull(productRestfulDao.updateProductById(product));
		
	}
	
	@Test
	public void shouldDeleteProductByIdReturnProductObject() {
		when(entityManager.find(Product.class,1)).thenReturn(product);
		doNothing().when(entityManager).remove(product);
		productRestfulDao.deleteProductById(1);
		
		verify(entityManager).find(Product.class, 1);
		verify(entityManager).remove(product);
	}
	
	@Test
	public void shouldfindAllProductReturnProductList() {
		List list = new ArrayList();
		Product p1 = new Product();
		p1.setProductId(1);
		p1.setProductName("pp");
		p1.setProductPrice("price");
		p1.setProductQuantity("131");
		list.add(p1);
		
		when(entityManager.createQuery("FROM Product",Product.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(list);
		
		Assert.assertEquals(list, productRestfulDao.findAllProduct());
		
		verify(entityManager).createQuery("FROM Product",Product.class);
		verify(query).getResultList();
	}
}
