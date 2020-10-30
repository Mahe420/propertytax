package com.project.propertytax.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.propertytax.entity.Uav;

public interface UavRepository extends JpaRepository<Uav, Integer> {

	@Query(value="select * from Uav u where u.description_id=?1 and u.zone_id=?2",nativeQuery=true)
	Uav findByDescriptionAndStatus(int id, int id2);

}
