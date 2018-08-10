package main.java.io.bhannur.api.service;

import main.java.io.bhannur.api.entity.EntertainmentItem;

import java.util.List;

public interface EntertainmentItemService {
    List<EntertainmentItem> findAll();

    EntertainmentItem createItem(EntertainmentItem item);

    EntertainmentItem updateItem(String id, EntertainmentItem item);

    EntertainmentItem findOneItem(String id);

    void deleteItem(String id);
}
