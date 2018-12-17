package com.karamihaylov.rest.webservices.restfulwebservices.daydream;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class DayDreamDaoService {

	private static List<DayDream> dreams = new ArrayList<>();
	
	private static int dreamsCount = 3;
	
	static {
		dreams.add(new DayDream( 1,"Tea" )); 
		dreams.add(new DayDream( 2,"korab" ));
		dreams.add(new DayDream( 3,"lodka" ));
		dreams.add(new DayDream( 4,"raketa" ));
	}
	public List<DayDream> findAll() {
		return dreams;
	}
	public DayDream save(DayDream dream) {
		if( dream.getID() == null ) {
			dream.setID( ++dreamsCount );
		}
		dreams.add(dream);
		return dream;
	}
	public DayDream findOne(int id) {
		for(DayDream dream:dreams) {
			if(dream.getID() == id) {
				return dream;	
			}
		}
		return null;
	}
	
	public DayDream removeById(int id) {
		Iterator<DayDream> iterator = dreams.iterator();
		while (iterator.hasNext()) {
			DayDream dream = iterator.next();
			if( dream.getID() == id ) {
				iterator.remove();
				return dream;
			}
		}
		return null;
	}
}
