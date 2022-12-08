package com.cybage.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table
public class ItemCategory {
	
	@Id
	@GeneratedValue
	private int itemCategoryId;
	private String itemCategoryName;
	private String itemCategoryImage;
	private String itemCategoryDescription;
	
	@OneToMany(mappedBy = "itemCategory",cascade = CascadeType.ALL)
	@JsonManagedReference(value = "itemCategoryJson")
	@JsonIgnore
	private List<Item> items;
}
