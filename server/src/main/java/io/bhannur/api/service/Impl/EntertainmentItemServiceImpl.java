package io.bhannur.api.service.Impl;

import io.bhannur.api.entity.EntertainmentItem;
import io.bhannur.api.exception.EntityNotFoundException;
import io.bhannur.api.repository.EntertainmentItemRepository;
import io.bhannur.api.service.EntertainmentItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EntertainmentItemServiceImpl implements EntertainmentItemService {


    @Autowired
    private EntertainmentItemRepository entertainmentItemRepository;

    @Override
    @Transactional(readOnly = true)
    public List<EntertainmentItem> findAll() {
        return entertainmentItemRepository.findAll();
    }

    @Override
    @Transactional
    public EntertainmentItem createItem(EntertainmentItem item) {
        return entertainmentItemRepository.createItem(item);
    }

    @Override
    @Transactional
    public EntertainmentItem updateItem(String id, EntertainmentItem item) {
        EntertainmentItem existingItem = entertainmentItemRepository.findOneItem(id);
        if(existingItem == null)
        {
            throw new EntityNotFoundException("Entertainment Item not found");
        }
        return entertainmentItemRepository.updateItem(item);
    }

    @Override
    @Transactional(readOnly = true)
    public EntertainmentItem findOneItem(String id) {
        return entertainmentItemRepository.findOneItem(id);
    }

    @Override
    @Transactional
    public void deleteItem(String id) {
        EntertainmentItem existingItem = entertainmentItemRepository.findOneItem(id);
        if(existingItem == null)
        {
            throw new EntityNotFoundException("Entertainment Item not found");
        }
        entertainmentItemRepository.deleteItem(existingItem);
    }
}
