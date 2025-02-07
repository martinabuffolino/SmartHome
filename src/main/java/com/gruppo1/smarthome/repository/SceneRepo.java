package com.gruppo1.smarthome.repository;
import com.gruppo1.smarthome.model.Scene;
import org.springframework.data.repository.CrudRepository;


public interface SceneRepo extends CrudRepository<Scene, String> {

    void deleteSceneById(String id);
}
