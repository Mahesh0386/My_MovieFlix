package io.bhannur.api.controller;

import io.bhannur.api.entity.EntertainmentItem;
import io.bhannur.api.exception.EntityNotFoundException;
import io.bhannur.api.service.EntertainmentItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value ="entItem")
public class EntertainmentItemController {

    @Autowired
    private EntertainmentItemService entertainmentItemService;

    @RequestMapping(method = RequestMethod.GET)
    public List<EntertainmentItem> findAll() {
        return entertainmentItemService.findAll();
    }


    @RequestMapping(method = RequestMethod.GET, value="{id}")
    public EntertainmentItem findOneItem(@PathVariable("id") String id)
    {
        EntertainmentItem item = entertainmentItemService.findOneItem(id);
        if(item == null)
        {
            throw new EntityNotFoundException("Entertainment Item not found");
        }
        return item;
    }

    @RequestMapping(method = RequestMethod.POST)
    public EntertainmentItem  createItem(@RequestBody EntertainmentItem item)
    {
        return entertainmentItemService.createItem(item);
    }

    @RequestMapping(method = RequestMethod.PUT,value = "{id}")
    public EntertainmentItem updateItem(@PathVariable("id") String id,@RequestBody EntertainmentItem item)
    {
        return entertainmentItemService.updateItem(id,item);

    }
    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public void deleteItem(@PathVariable("id") String id) {

        entertainmentItemService.deleteItem(id);
    }


}
