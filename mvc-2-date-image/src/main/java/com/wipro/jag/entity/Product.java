package com.wipro.jag.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.apache.tomcat.util.codec.binary.Base64;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	private Double price;
	private Date mfgDate;
	@Lob
	private byte[] image;
	
	public Product() {}

	public Product(Integer id, String name, Double price, Date mfgDate, byte[] image) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.mfgDate = mfgDate;
		this.image = image;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getMfgDate() {
		return mfgDate;
	}
	
	public String getMfgDate1()
	{
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy");
		return sdf.format(mfgDate);
	}

	public void setMfgDate(Date mfgDate) {
		this.mfgDate = mfgDate;
	}

	public byte[] getImage() {
		return image;
	}
	
	public String getImage1()
	{
		return Base64.encodeBase64String(image);
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", mfgDate=" + mfgDate + ", image="
				+ image.length + "]";
	}
	
}
