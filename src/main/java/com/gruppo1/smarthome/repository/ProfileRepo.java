package com.gruppo1.smarthome.repository;
import com.gruppo1.smarthome.model.Profile;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepo extends CrudRepository<Profile, String> {
    void deleteProfileById(String id);
}