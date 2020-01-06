package com.nikhil.cloth;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Shirt")
public class ShirtController {

	@Autowired
	private ShirtRepo shirtRepo;

	@GetMapping(value = "/All")
	@ResponseBody
	public List<Shirt> getAllShirt() {

		return shirtRepo.findAll();
	}

	@RequestMapping( value = "/ShirtEntry")
	public String putShirtDetails(@RequestParam MultiValueMap<String, Object> customQuery) {
		String skuId = customQuery.get("skuId").get(0).toString();
		System.out.println(shirtRepo.findBySkuId(skuId));
		if (!shirtRepo.findBySkuId(skuId).isEmpty()) {
			shirtRepo.updateQuantity(shirtRepo.findBySkuId(skuId).get(0).getTotalQuantity() + 1, skuId);
		} else {
			shirtRepo.save(new Shirt(skuId, customQuery.get("brandName").get(0).toString(),
					Double.valueOf(customQuery.get("price").get(0).toString()),
					customQuery.get("color").get(0).toString(),
					Integer.valueOf(customQuery.get("size").get(0).toString()), 1));
		}
		
		if (!shirtRepo.findBySkuId(skuId).isEmpty()) {
			return "Inserted";
		}else {
			return "Failed to Insert";
		}

	}

	@GetMapping(value = "/SkuId")
	public @ResponseBody List<Shirt> getBySkuId(@RequestParam("skuId") String skuId) {
		return shirtRepo.findBySkuId(skuId);
	}
	
	@GetMapping(value = "/Buy/SkuId")
	public @ResponseBody String buySkuId(@RequestParam("skuId") String skuId) {
		if (!shirtRepo.findBySkuId(skuId).isEmpty() && shirtRepo.findBySkuId(skuId).get(0).getTotalQuantity()>0 ) {
			shirtRepo.updateQuantity(shirtRepo.findBySkuId(skuId).get(0).getTotalQuantity() - 1, skuId);
		} else {
			return "Already Sold!! Try Next Time";
		}
		return"purchase SuccessFull";
	}
	

	@GetMapping(value = "/Price")
	@ResponseBody
	public List<Shirt> getByPrice(@RequestParam("price") Double price) {
		return shirtRepo.groupByPrice(price);
	}

	@GetMapping(value = "/Color")
	@ResponseBody
	public List<Shirt> getByColor(@RequestParam("color") String color) {
		return shirtRepo.groupByBrandName(color);
	}

	@GetMapping(value = "/Size")
	@ResponseBody
	public List<Shirt> getByBrand(@RequestParam("size") int size) {
		return shirtRepo.groupBySize(size);
	}

	@GetMapping(value = "/BrandName")
	@ResponseBody
	public List<Shirt> getByBrand(@RequestParam("brandName") String brandName) {
		return shirtRepo.groupByBrandName(brandName);
	}

}
