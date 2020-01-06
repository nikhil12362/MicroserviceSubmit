package com.nikhil.cloth;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@SpringBootTest
class ClothServiceApplicationTests {
	@Autowired
	ClothServiceController sc;

	@Test
	void skuIDTest() {

		MultiValueMap<String, Object> args = new LinkedMultiValueMap<>();
		args.add("skuId", "alpha");
		Assert.assertFalse((((List) sc.getShirts("/", args))).isEmpty());

	}

	@Test
	void brandTest() {

		MultiValueMap<String, Object> args = new LinkedMultiValueMap<>();
		args.add("brandName", "levis");
		Assert.assertFalse((((List) sc.getShirts("/", args))).isEmpty());

	}

	@Test
	void colorTest() {

		MultiValueMap<String, Object> args = new LinkedMultiValueMap<>();
		args.add("color", "blue");
		Assert.assertFalse((((List) sc.getShirts("/", args))).isEmpty());

	}

	@Test
	void priceTest() {

		MultiValueMap<String, Object> args = new LinkedMultiValueMap<>();
		args.add("price", "3000");
		Assert.assertFalse((((List) sc.getShirts("/", args))).isEmpty());
	}

	@Test
	void sizeTest() {

		MultiValueMap<String, Object> args = new LinkedMultiValueMap<>();
		args.add("size", "42");
		Assert.assertFalse((((List) sc.getShirts("/", args))).isEmpty());
	}
}
