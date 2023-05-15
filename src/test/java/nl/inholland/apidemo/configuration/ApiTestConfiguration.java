package nl.inholland.apidemo.configuration;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;

@TestConfiguration
public class ApiTestConfiguration {

    @MockBean
    private JwtTokenProvider jwtTokenProvider;
}

