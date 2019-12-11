package bd.clinic.modules.restTemplate

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.web.client.RestTemplate

@Configuration
class RestTemplateConfig {

    @Bean
    @Primary
    fun provideDefaultRestTemplate(): RestTemplate {
        return RestTemplate()
    }
}