package com.javacode;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

class SaleDAOTest {

	private SaleDAO dao;
	
	@BeforeEach
	void setUp() throws Exception {
		 DriverManagerDataSource datasource = new DriverManagerDataSource();
		 datasource.setUrl("jdbc:mysql://localhost:3306/xe");
		 datasource.setUsername("root");
		 datasource.setPassword("123456");
		 datasource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		 
		 dao = new SaleDAO(new JdbcTemplate(datasource));
	}

	@Test
	void testGetList() {
		List<Sale> listSale = dao.getList();
		
		assertTrue(listSale.isEmpty());
	}

	@Test
	void testSave() {
		fail("Not yet implemented");
	}

	@Test
	void testGet() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteByID() {
		fail("Not yet implemented");
	}

}
