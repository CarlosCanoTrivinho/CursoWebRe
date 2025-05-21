package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.Test;

class pruebiRandom {

	@Test
	void test() {
		boolean bajo=false;
		boolean alto=false;
		
		Random random=new Random();
		for(int i=0;i<100;i++) {
			int nextInt = random.nextInt(21);
			System.out.print(nextInt+" ");
			if(nextInt==20) {
				alto=true;
			}
			if(nextInt==0) {
				bajo=true;
			}
			
			if(nextInt==21) {
				fail("ha salido 21!!");
			}
		}
		assertTrue(alto);
		assertTrue(bajo);
		
	}

}
