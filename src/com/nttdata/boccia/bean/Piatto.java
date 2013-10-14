package com.nttdata.boccia.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;

@Entity
public class Piatto {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Key key;
	
	private Menu menu;
	
	private String name;
}
