package com.javacode;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class SaleDAO {

	private JdbcTemplate jdbcTemplate;

	public SaleDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Sale> getList() {
		String sql = "SELECT * FROM sales";
		List<Sale> listSale = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Sale.class));
		
		return listSale;
	}

	public void save(Sale sale) {

	}

	public Sale get(Long id) {
		return null;
	}

	public void update(Sale sale) {

	}

	public void deleteByID(Long id) {

	}

}
