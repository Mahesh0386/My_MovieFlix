package main.java.io.bhannur.api.controller;

import main.java.io.bhannur.api.entity.Shows;
import main.java.io.bhannur.api.service.ShowsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value ="shows")
public class ShowController {

    @Autowired
    private ShowsService showsService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Shows> findAll() {
        return showsService.findAll();
    }

}
