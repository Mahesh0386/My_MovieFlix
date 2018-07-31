package main.java.io.bhannur.api.service;

import main.java.io.bhannur.api.entity.Shows;
import main.java.io.bhannur.api.repository.ShowsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowServiceImpl implements ShowsService {


    @Autowired
    private ShowsRepository showsRepository;

    @Override
    public List<Shows> findAll() {
        return null;
    }
}
