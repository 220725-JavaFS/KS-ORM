package com.revature;

import com.revature.models.Bottoms;
import com.revature.models.Shoes;
import com.revature.models.Tops;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("Test");
		
		Tops top1 = new Tops("tank top", "blue", 'M', 1000, 1.99, 19.99);
		Bottoms bottoms1 = new Bottoms("jeans", "dark-wash", 6, 750, 7.99, 29.99);
		Shoes shoe1 = new Shoes("vans", "checkered", 8.5, 500, 14.95, 44.99);
		
		System.out.println(top1 + "\n" + bottoms1 + "\n" + shoe1);
		
		Tops top2 = new Tops();
		Bottoms bottoms2 = new Bottoms();
		Shoes shoe2 = new Shoes();
		
		System.out.println("\n" + top2 + "\n" + bottoms2 + "\n" + shoe2);

	}

}
