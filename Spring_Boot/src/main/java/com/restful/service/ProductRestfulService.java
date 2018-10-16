package com.restful.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restful.daointerface.ProductRestfulDaoInterface;
import com.restful.model.Product;
import com.restful.serviceinterface.ProductRestfulServiceInterface;

@Service
@Transactional
public class ProductRestfulService implements ProductRestfulServiceInterface {
	
	@Autowired
	private ProductRestfulDaoInterface productRestfulDaoInterface;

	@Override
	public Product addproduct(Product product) {
		return productRestfulDaoInterface.addproduct(product);
	}

	@Override
	public Product findById(Integer productId) {
		return productRestfulDaoInterface.findById(productId);
	}

	@Override
	public Product updateProductById(Product product) {
		return productRestfulDaoInterface.updateProductById(product);
	}

	@Override
	public void deleteProductById(Integer productId) {
		productRestfulDaoInterface.deleteProductById(productId);
	}

	@Override
	public List<Product> findAllProduct() {
		return productRestfulDaoInterface.findAllProduct();
	}
	}

	

