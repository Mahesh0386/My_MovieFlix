package main.java.io.bhannur.api.repository;

import main.java.io.bhannur.api.entity.EntertainmentItem;

import java.util.List;

public interface EntertainmentItemDao {
     List<EntertainmentItem> findAll();

     EntertainmentItem createItem(EntertainmentItem item);

     EntertainmentItem updateItem(EntertainmentItem item);

     EntertainmentItem findOneItem(String id);
}
