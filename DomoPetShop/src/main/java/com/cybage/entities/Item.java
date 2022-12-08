package com.cybage.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Item {
	
	@Id
	@GeneratedValue
	private int itemId;
	
	private String categoryName;
	private String itemName;
	private double itemPrice;
	private String itemDescription;
	private String itemImage;
	
	@ManyToOne
	@JoinColumn(name = "item_category_id")
	@JsonBackReference(value = "itemCategoryJson")
	private ItemCategory itemCategory;
	
	@ManyToMany
	@JoinColumn(name = "id")
	@JsonBackReference(value = "itemJson")
	private List<FavouriteItem> favouriteItem;
}
