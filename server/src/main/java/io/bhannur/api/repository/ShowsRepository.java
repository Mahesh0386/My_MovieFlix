package main.java.io.bhannur.api.repository;

import main.java.io.bhannur.api.entity.Shows;

import java.util.List;

public interface ShowsRepository {
     List<Shows> findAll();
}
