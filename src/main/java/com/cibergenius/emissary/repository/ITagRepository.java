package com.cibergenius.emissary.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cibergenius.emissary.entities.TagModel;

public interface ITagRepository extends CrudRepository<TagModel, Long> {

    Set<TagModel> findByEPC(String epc);

    Set<TagModel> findBypublishDateIsNull();

    Set<TagModel> findBylogicalDevices(String logical_devices);

    @Query(nativeQuery = true, value = "select * from tags where logical_devices like %?1% and publish_date is null AND (SUBSTRING(log,1,5) IS NULL OR SUBSTRING(log,1,5) <> 'Error') ")
    Set<TagModel> getLogicalDevicesPublishDateNull(String logical_devices);
}
