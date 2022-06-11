package com.javacode.TechPolyShop.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;

	@Column(columnDefinition = "varchar(100) not null")
	private String name;

	@Column(nullable = false)
	private int quantity;

	@Column(nullable = false)
	private double unitPrice;

	@Column(length = 200)
	private String image;

	@Column(columnDefinition = "varchar(500) not null")
	private String description;

	@Column(nullable = false)
	private double discount;

	@Temporal(TemporalType.DATE)
	private Date enteredDate;

	@Column(nullable = false)
	private short status;
	
	@ManyToOne
	@JoinColumn(name = "category")
	private Category category;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private Set<OrderDetail> orderDetails;
}
