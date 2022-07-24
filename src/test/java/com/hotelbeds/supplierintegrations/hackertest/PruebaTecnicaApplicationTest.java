package com.hotelbeds.supplierintegrations.hackertest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PruebaTecnicaApplicationTest {

	@Test
	void testMain() {
		Exception ex = null;
		try {
		PruebaTecnicaApplication.main(new String[] {});
		}catch (Exception e) {
			ex = e;
		}
		
		assertThat(ex).as("testMain").isNull();
	}
}
