package dao;

import java.sql.SQLException;
import java.util.List;
import model.Item;

public interface ItemDAO {

	List<Item> findAllItems();

	List<Item> searchItemsByKeyword(String keyWord);

	List<Item> findItemsByCategory(String category);
	
	List<Item> findItemsByBrand(String brand);

	void insert(Item item);

	void delete(String itemID);
}
