package tripvibe.tripvibebe.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//==login test==//
// 이걸로 @CrossOrigin(origins = "http://localhost:3000") 제거해도됨
@Configuration
public class MvcConfigurer implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns()
                .allowedHeaders("*")
                .exposedHeaders("*");
    }
}
