package nl.inholland.apidemo.configuration;

import nl.inholland.apidemo.jwt.JwtKeyProvider;
import nl.inholland.apidemo.jwt.JwtTokenProvider;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
@TestConfiguration
public class ApiTestConfiguration {
    @MockBean
    public JwtTokenProvider jwtTokenProvider;
}
