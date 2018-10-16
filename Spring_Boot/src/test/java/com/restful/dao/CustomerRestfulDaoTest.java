package com.restful.dao;

import static org.junit.Assert.fail;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.restful.model.Customer;
import com.restful.model.Product;

import org.junit.Assert;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;


@RunWith(MockitoJUnitRunner.class)
public class CustomerRestfulDaoTest {
	
	@InjectMocks
	CustomerRestfulDao customerRestfulDao;
	
	@Mock
	EntityManager entityManager;
	
	@Mock
	Customer customer;
	
	@Mock
	TypedQuery<Customer> query;
	
	@Test
	public void shouldAddCustomerAddTheCustomerAndReturnCustomer() {
		doNothing().when(entityManager).persist(customer);
		
		Assert.assertEquals(customer, customerRestfulDao.addCustomer(customer));
		
		verify(entityManager).persist(customer);
	}
	
	@Test
	public void shouldfindByIdReturnCustomer() {
		when(entityManager.find(Customer.class, 1)).thenReturn(customer);
		
		Assert.assertEquals(customer, customerRestfulDao.findById(1));
		
		verify(entityManager).find(Customer.class, 1);
	}

	@Test
	public void shouldDeleteMethodDeleteCutomer() {
		when(entityManager.find(Customer.class, 1)).thenReturn(customer);
		doNothing().when(entityManager).remove(customer);
		
		customerRestfulDao.delete(1);
		
		verify(entityManager).find(Customer.class, 1);
		verify(entityManager).remove(customer);
	}
	
	@Test
	public void shouldUpdateMethodUpadateCutomer() {
		when(entityManager.find(Customer.class, 1)).thenReturn(customer);
		when(entityManager.createQuery(Mockito.anyString())).thenReturn(query);
		when(query.executeUpdate()).thenReturn(1);

		customerRestfulDao.update(customer);
		
		verify(entityManager).find(Customer.class, 1);
		verify(query).executeUpdate();
	}

}
