package hello.helloController;


import hello.helloController.Controller.HelloController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;




@WebMvcTest(controllers = HelloController.class)
class HelloControllerApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Test
	void hello가_리턴이_성공하는걸_확인() throws Exception {
		String hello = "hello";
		mvc.perform(get("/hello")).andExpect(status().isOk()).andExpect(content().string(hello));


	}

	@Test
	void helloDto가_리턴성공() throws Exception {
		String name = "hello";
		int amount = 1000;

		mvc.perform(get("/hello/dto")
						.param("name", name)
						.param("amount", String.valueOf(amount))
				)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", is(name)).exists())
				.andExpect(jsonPath("$.amount", is(amount)).exists());
	}




}
