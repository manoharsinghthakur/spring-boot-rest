package com.restful.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.restful.daointerface.ProductRestfulDaoInterface;
import com.restful.model.Customer;
import com.restful.model.Product;

@Repository
public class ProductRestfulDao implements ProductRestfulDaoInterface {
	
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public Product addproduct(Product product) {
		entityManager.persist(product);
		return product;
	}

	@Override
	public Product findById(Integer productId) {
		System.out.println("find by id product..");
		
		return (Product) entityManager.find(Product.class, productId);
	}

	@Override
	public Product updateProductById(Product product) {
		Product newProductObj = findById(product.getProductId());
		if(newProductObj != null)
		{
			
			Query query= entityManager.createQuery("update Product set productName= '"+product.getProductName()+"' , productPrice='"+product.getProductPrice()+"' , productQuantity = '"+product.getProductQuantity()+"' where productId='"+product.getProductId()+"' ");
			query.executeUpdate();
		
		}
		
		return product;
		
	}

	@Override
	public void deleteProductById(Integer productId) {
		
		Product productObj = findById(productId);
		if(productObj != null)
		{
			entityManager.remove(productObj);
		}
	}

	@Override
	public List<Product> findAllProduct() {
		List<Product> productList= entityManager.createQuery("FROM Product",Product.class).getResultList();
		
		return productList;
	}

	


	
	

}
