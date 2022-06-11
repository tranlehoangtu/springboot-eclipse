package com.javacode.TechPolyShop.model;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

@SuppressWarnings("serial")

public class CategoryDTO implements Serializable {
	private Long categoryId;
	@NotEmpty
	@Length(min = 5)
	private String name;
	private Boolean isEdit = false;

	public CategoryDTO() {
		super();
	}

	public CategoryDTO(Long categoryId, @NotEmpty @Min(5) String name, Boolean isEdit) {
		super();
		this.categoryId = categoryId;
		this.name = name;
		this.isEdit = isEdit;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsEdit() {
		return isEdit;
	}

	public void setIsEdit(Boolean isEdit) {
		this.isEdit = isEdit;
	}

	@Override
	public String toString() {
		return "CategoryDTO [categoryId=" + categoryId + ", name=" + name + ", isEdit=" + isEdit + "]";
	}

}
