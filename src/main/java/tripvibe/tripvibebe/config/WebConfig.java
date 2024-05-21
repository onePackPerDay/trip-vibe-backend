package tripvibe.tripvibebe.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer { //react 연동을 위한 cors 설정

    @Override
    public void addCorsMappings(CorsRegistry registry) { //cors를 허용할 경로 패턴 추가 메서드
        registry.addMapping("/**") //서버에 온 모든 경로 허용
                .allowedOrigins("*") //어떤 도메인에서 요청을 보내도 ok
                .allowedMethods("GET", "POST", "PUT", "DELETE");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image/**") //이 도메인으로 요청되는 리소스는
                .addResourceLocations("file:///fullstack/image/"); //이 서버 주소에 있는 리소스를 돌려준다.
    }
}
