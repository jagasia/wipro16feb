package com.wipro.jag.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.jag.entity.Product;
import com.wipro.jag.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository pr;
	
	public Product create(Product product) {
		return pr.save(product);
	}
	public List<Product> read() {
		return pr.findAll();
	}
	public Product read(Integer id) {
		Optional<Product> temp = pr.findById(id);
		Product p=null;
		if(temp.isPresent())
			p=temp.get();
		return p;
	}
	public Product update(Product product) {
		Product p = read(product.getId());
		if(p!=null)
			pr.save(p);
		return p;
	}
	public Product delete(Integer id) {
		Product p = read(id);
		if(p!=null)
			pr.delete(p);
		return p;
	}
	
}
