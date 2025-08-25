package com.pedroviena.api_test_showcase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc; // 2. Importe o AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc; // 3. Importe o MockMvc

import com.pedroviena.api_test_showcase.controller.UserController;
import com.pedroviena.api_test_showcase.model.User;
import com.pedroviena.api_test_showcase.service.UserService;

import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.loader.PactFolder;
import au.com.dius.pact.provider.spring.junit5.MockMvcTestTarget; // 1. Importe o MockMvcTestTarget
import au.com.dius.pact.provider.spring.spring6.PactVerificationSpring6Provider;

@Provider("UserApiProvider")
@PactFolder("target/pacts")
@SpringBootTest
@AutoConfigureMockMvc
public class UserProviderContractTest extends AbstractIntegrationTest {

    
    
    @Autowired
    private MockMvc mockMvc; // 6. Injete o MockMvc

    @Autowired
    private UserService userService;

    @BeforeEach
    void setUp(PactVerificationContext context) {
        if (context != null) {
           
            MockMvcTestTarget target = new MockMvcTestTarget();
            target.setControllers(new UserController(userService));
            context.setTarget(target);
        }
    }

    @TestTemplate
    @ExtendWith(PactVerificationSpring6Provider.class)
    void pactVerificationTestTemplate(PactVerificationContext context) {
        context.verifyInteraction();
    }

    @State("um usuário com ID 1 existe")
    public void userExistsState() {
        
    userService.deleteAll();
    User user = new User();
    user.setName("Ana Silva");
    user.setEmail("ana.silva@example.com");
    userService.save(user);
    }
}