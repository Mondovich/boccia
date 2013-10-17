package com.nttdata.boccia.model.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;

@Entity
public class Menu {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Key key;
	private Date date;
	private List<String> primi;
	private List<String> secondi;
	private List<String> contorni;
	
	public Key getKey() {
		return key;
	}
	public void setKey(Key key) {
		this.key = key;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public List<String> getPrimi() {
		return primi;
	}
	public void setPrimi(List<String> primi) {
		this.primi = primi;
	}
	public List<String> getSecondi() {
		return secondi;
	}
	public void setSecondi(List<String> secondi) {
		this.secondi = secondi;
	}
	public List<String> getContorni() {
		return contorni;
	}
	public void setContorni(List<String> contorni) {
		this.contorni = contorni;
	}

	@Override
	public String toString() {
		return "Menu [key=" + key + ", date=" + date + ", primi=" + primi
				+ ", secondi=" + secondi + ", contorni=" + contorni + "]";
	}
}
