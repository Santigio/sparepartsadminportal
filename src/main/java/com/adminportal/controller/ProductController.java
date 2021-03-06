package com.adminportal.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.adminportal.domain.Product;
import com.adminportal.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String addProduct(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		return "addProduct";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String addProductPost(
			@ModelAttribute("product") Product product, HttpServletRequest request
			) {
		productService.save(product);
		
		MultipartFile productImage = product.getProductImage();
		
		try {
			byte[] bytes = productImage.getBytes();
			String name = product.getId()+".png";
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream (new File("src/main/resources/static/image/product/"+name)));
			stream.write(bytes);
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return "redirect:productList";

}
	@RequestMapping("/productList")
	public String productList(Model model) {
	List<Product> productList = productService.findAll();
	model.addAttribute("productList", productList);
		return "productList";
	}
	
	
}












