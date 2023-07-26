package com.example.demo.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "")
public class Genres {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int catid;
	private String cat;
	public String getCat() {
		return cat;
	}
	public int getCatid() {
		return catid;
	}
	public void setCatid(int catid) {
		this.catid = catid;
	}
	public void setCat(String cat) {
		this.cat = cat;
	}
	public Genres() {
		super();
		// TODO Auto-generated constructor stub
	}
}
