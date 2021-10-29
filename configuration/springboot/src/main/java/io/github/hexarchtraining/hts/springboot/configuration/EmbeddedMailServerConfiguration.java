package io.github.hexarchtraining.hts.springboot.configuration;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(MailProperties.class)
public class EmbeddedMailServerConfiguration {

  @Bean(initMethod = "start", destroyMethod = "stop")
  public GreenMail embeddedMailServer(MailProperties mailProperties) {
    ServerSetup serverSetup = new ServerSetup(mailProperties.getPort(), null, mailProperties.getProtocol());
    return new GreenMail(serverSetup);
  }

}
