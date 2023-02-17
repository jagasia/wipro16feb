package com.wipro.jag.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.annotation.MultipartConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.wipro.jag.entity.Product;
import com.wipro.jag.service.ProductService;

@Controller
@MultipartConfig
public class ProductController {
	@Autowired
	private ProductService ps;
	
	@GetMapping
	public ModelAndView home()
	{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("index");
		mv.addObject("product", new Product());
		List<Product> products = ps.read();
		mv.addObject("products",products);
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "dml", params="add")
	public ModelAndView addProduct(@RequestParam("name") String name,@RequestParam("price") Double price,@RequestParam("mfgDate") String dt,@RequestParam("image") MultipartFile file) throws IOException, ParseException
	{
		
		Product product=new Product();
		product.setName(name);
		product.setPrice(price);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		product.setMfgDate(sdf.parse(dt));
		product.setImage(file.getBytes());
		System.out.println(product);
		ps.create(product);
		return home();
	}
	
	
}
