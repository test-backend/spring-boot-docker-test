package com.backend.springboot.docker;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class SpringBootDockerTest {

  @Test
  public void testApplication() {
    log.info("It works!");
  }
}
