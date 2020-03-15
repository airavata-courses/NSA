package com.nsa.app;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nsa.app.model.User;
import com.nsa.app.repository.UserRepository;
import org.junit.*;

import static org.junit.Assert.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.Mockito.when;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;



@RunWith(SpringRunner.class)
@SpringBootTest(classes=ApplicationContext.class)
@AutoConfigureMockMvc
public class KafkaConsumerApplicationTests {

//	@Test
//	void contextLoads() {
//	}
	
	
	@MockBean
	UserRepository userrepo;

	@MockBean
	KafkaConsumer kafkaconsumer;
	
	
	static User user;
	@BeforeAll
	public static void setUp() {
		user = new User();
		user.setUserName("NikitaB");
		user.setLastName("Bafna");
		user.setFirstName("Nikita");
		user.setEmail("niki@gmail.com");
		
	}
	
	@Test
	public void userTest() {
		System.out.println("**********START OF TESTCASE 1 ***************");
		assertEquals(user.getUserName(),"NikitaB");
		assertEquals(user.getLastName(),"Bafna");
		assertEquals(user.getFirstName(),"Nikita");
		assertEquals(user.getEmail(),"niki@gmail.com");
		
	}
	
	
	@Test
	public void getUserByUserNameTest() {
		System.out.println("**********START OF TESTCASE 2 ***************");
		when(userrepo.findByuserName("NikitaB")).thenReturn(user);
		assertEquals("NikitaB",user.getUserName());
		
	}
	
}

