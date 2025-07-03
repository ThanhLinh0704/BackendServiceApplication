package linhlt.project.backend_service.service;

import linhlt.project.backend_service.config.MockConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@AutoConfigureMockMvc
@Import(MockConfig.class)
@Slf4j
public class UserServiceTest {
}
