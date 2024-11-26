package HyEnd.Proj.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /*
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:63342") // 프론트엔드 주소로 변경
                .allowedMethods("*")
                .allowCredentials(true);
    }

     */

}
