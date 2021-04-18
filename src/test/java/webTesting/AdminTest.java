package webTesting;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.adminportal.controller.HomeController;

public class AdminTest {
	
	private MockMvc mockMvc;
	@InjectMocks
	private HomeController homeController;
	
	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(homeController)
				.build();
		
	}

	// testing weather the index object is working 
		@Test
		public void testIndexPage() throws Exception {
			mockMvc.perform(
					MockMvcRequestBuilders.get("/")
					)
						.andExpect(MockMvcResultMatchers.status().isOk())
						.andExpect(MockMvcResultMatchers.content().string("redirect:/home"));
						
		
		}
		@Test
		public void testhomePage() throws Exception {
			mockMvc.perform(
					MockMvcRequestBuilders.get("/home")
					)
						.andExpect(MockMvcResultMatchers.status().isOk())
						.andExpect(MockMvcResultMatchers.content().string("home"));
						
		
		}
		@Test
		public void testloginPage() throws Exception {
			mockMvc.perform(
					MockMvcRequestBuilders.get("/")
					)
						.andExpect(MockMvcResultMatchers.status().isOk())
						.andExpect(MockMvcResultMatchers.content().string("login"));
						
		
		}
}
