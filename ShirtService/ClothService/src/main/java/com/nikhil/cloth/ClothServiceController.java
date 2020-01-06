package com.nikhil.cloth;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/Cloth")
public class ClothServiceController {

	@Autowired
	private LoadBalancerClient loadBalancerClient;

	@RequestMapping( value = "/Shirt/{api}")
	public @ResponseBody Object getShirts(@PathVariable(name = "api") String api,
			@RequestParam MultiValueMap<String, Object> customQuery) {
		ServiceInstance serviceInstance = loadBalancerClient.choose("ShirtServiceApplication");
		String baseUrl = serviceInstance.getUri().toString();
		baseUrl = baseUrl + "/Shirt/";
		RestTemplate restTemplate = new RestTemplate();
		Object response = null;
		try {
			String query = "";
			boolean added = false;
			boolean hasMore = false;

			if (customQuery.containsKey("skuId")) {
				query = query + "skuId=" + customQuery.get("skuId").get(0);
				added = true;
			}
			if (customQuery.containsKey("brandName")) {
				if (added) {
					hasMore = true;
					query = query + "&brandName=" + customQuery.get("brandName").get(0);
				} else {
					hasMore = true;
					api = "/BrandName";
					query = query + "brandName=" + customQuery.get("brandName").get(0);
				}
			}
			if (customQuery.containsKey("price")) {
				if (added) {
					hasMore = true;
					query = query + "&price=" + customQuery.get("price").get(0);
				} else {
					hasMore = true;
					api = "/Price";
					query = query + "price=" + customQuery.get("price").get(0);
				}
			}
			if (customQuery.containsKey("color")) {
				if (added) {
					hasMore = true;
					query = query + "&color=" + customQuery.get("color").get(0);
				} else {
					hasMore = true;
					api = "/Color";
					query = query + "color=" + customQuery.get("color").get(0);
				}
			}
			if (customQuery.containsKey("size")) {
				if (added) {
					hasMore = true;
					query = query + "&size=" + customQuery.get("size").get(0);
				} else {
					hasMore = true;
					api = "/Size";
					query = query + "size=" + customQuery.get("size").get(0);
				}
			}
			if (!added && !hasMore) {
				api = "/SkuId";
			}

			response = restTemplate.getForObject(baseUrl + api + "?" + query, List.class);
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return response;
	}
	
	
	@RequestMapping( value = "/Shirt/Buy/{api}")
	public @ResponseBody Object buyShirts(@PathVariable(name = "api") String api,
			@RequestParam MultiValueMap<String, Object> customQuery) {
		ServiceInstance serviceInstance = loadBalancerClient.choose("ShirtServiceApplication");
		String baseUrl = serviceInstance.getUri().toString();
		baseUrl = baseUrl + "/Shirt/Buy/";
		RestTemplate restTemplate = new RestTemplate();
		Object response = null;
		try {
			String query = "";
			
			if (customQuery.containsKey("skuId")) {
				query = query + "skuId=" + customQuery.get("skuId").get(0);				
			}

			response = restTemplate.getForObject(baseUrl + api + "?" + query, List.class);
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return response;
	}

	
	

}
