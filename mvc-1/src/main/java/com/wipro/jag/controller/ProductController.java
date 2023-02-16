package com.wipro.jag.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wipro.jag.model.Product;
import com.wipro.jag.model.ProductRepository;

@Controller
public class ProductController {
	@Autowired
	private ProductRepository pdao;
	
	@GetMapping("/showProduct")
	public ModelAndView showProductPage()
	{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("product");
		//obtain all products from the table
		List<Product> products = pdao.findAll();
		mv.addObject("products", products);
		mv.addObject("product", new Product());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "dml", params = "add")
	public ModelAndView addProduct(Product product)
	{
		pdao.save(product);
		return showProductPage();
	}

	@RequestMapping(method = RequestMethod.POST, value = "dml", params = "update")
	public ModelAndView updateProduct(Product product)
	{
		pdao.save(product);
		
		return selectProduct(product.getId());
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "dml", params = "delete")
	public ModelAndView deleteProduct(Product product)
	{
		pdao.delete(product);
		return showProductPage();
	}
	
	@GetMapping("select")
	public ModelAndView selectProduct(@RequestParam("id") Integer id)
	{
		Optional<Product> temp = pdao.findById(id);
		Product p=null;
		if(temp.isPresent())
			p=temp.get();
		ModelAndView mv=new ModelAndView();
		mv.setViewName("product");
		
		//obtain all products from the table
		List<Product> products = pdao.findAll();
		mv.addObject("products", products);
		mv.addObject("product",p);
		return mv;
	}
	
}
