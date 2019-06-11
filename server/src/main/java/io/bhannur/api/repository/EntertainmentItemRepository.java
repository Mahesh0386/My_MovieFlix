package io.bhannur.api.repository;

import io.bhannur.api.entity.EntertainmentItem;

import java.util.List;

public interface EntertainmentItemRepository {
     List<EntertainmentItem> findAll();

     EntertainmentItem createItem(EntertainmentItem item);

     EntertainmentItem updateItem(EntertainmentItem item);

     EntertainmentItem findOneItem(String id);

     void deleteItem(EntertainmentItem existingItem);
}
