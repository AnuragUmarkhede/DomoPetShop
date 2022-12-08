
package com.cybage.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cybage.daos.FavouriteItemRepository;
import com.cybage.daos.ItemRepository;
import com.cybage.daos.UserRepository;
import com.cybage.entities.FavouriteItem;
import com.cybage.entities.Item;
import com.cybage.entities.User;

@Service
public class FavouriteListServiceImpl implements IFavouriteListService {

	@Autowired
	FavouriteItemRepository favouriteItemRepository;
	
	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	ItemServiceImpl itemServiceImpl;
	
	@Autowired
	UserRepository userRepository;

	@Override
	public FavouriteItem addToFavouriteList(int id, String userEmail) {
		FavouriteItem favouriteItem = new FavouriteItem();
		List<FavouriteItem> favouriteItemList = new ArrayList<>();
		
		List<Item> itemList = new ArrayList<>();

		User user = userRepository.findByUserEmail(userEmail);
		
		Item item = itemRepository.findByItemId(id);
		
		System.out.println(item);
		
		itemList.add(itemServiceImpl.findByItemId(id));
		favouriteItem.setItems(itemList);
		favouriteItemList.add(favouriteItem);
		item.getFavouriteItem().addAll(favouriteItemList);
		favouriteItem.setUserMail(user);
		return favouriteItemRepository.save(favouriteItem);
		
	}

	@Override
	public List<FavouriteItem> getFavouriteList(String userEmail) {
		return favouriteItemRepository.getFavouriteList(userEmail);
	}

	@Override
	public void removeFavouriteItem(int id,String userEmail) 
	{
		List<FavouriteItem> favouriteItems = favouriteItemRepository.getFavouriteList(userEmail);
		FavouriteItem item = null;
		for (FavouriteItem item1 : favouriteItems) {
			if (item1.getId() == id) {
				item = item1;
			}
		}
		favouriteItems.remove(item);
		favouriteItemRepository.delete(item);
		favouriteItemRepository.saveAll(favouriteItems);
	}

	@Override
	public void clearFavouriteList(String userEmail) {
		List<FavouriteItem> favouriteList = favouriteItemRepository.getFavouriteList(userEmail);
		favouriteItemRepository.deleteAll(favouriteList);
	}
	
}