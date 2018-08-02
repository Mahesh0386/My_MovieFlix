package main.java.io.bhannur.api.service;

import main.java.io.bhannur.api.entity.EntertainmentItem;
import main.java.io.bhannur.api.exception.EntityNotFoundException;
import main.java.io.bhannur.api.repository.EntertainmentItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EntertainmentItemServiceImpl implements EntertainmentItemService {


    @Autowired
    private EntertainmentItemDao entertainmentItemDao;

    @Override
    @Transactional(readOnly = true)
    public List<EntertainmentItem> findAll() {
        return entertainmentItemDao.findAll();
    }

    @Override
    @Transactional
    public EntertainmentItem createItem(EntertainmentItem item) {
        return entertainmentItemDao.createItem(item);
    }

    @Override
    @Transactional
    public EntertainmentItem updateItem(String id, EntertainmentItem item) {
        EntertainmentItem existingItem = entertainmentItemDao.findOneItem(id);
        if(existingItem == null)
        {
            throw new EntityNotFoundException("Entertainment Item not found");
        }
        return entertainmentItemDao.updateItem(item);
    }

    @Override
    public EntertainmentItem findOneItem(String id) {
        return entertainmentItemDao.findOneItem(id);
    }
}
