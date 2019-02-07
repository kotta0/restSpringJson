package com.karamihaylov.rest.webservices.restfulwebservices.daydream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DayDreamRepository extends JpaRepository<DayDream,Integer> {
	

}
