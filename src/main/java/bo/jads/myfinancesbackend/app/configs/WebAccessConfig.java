package bo.jads.myfinancesbackend.app.configs;

import bo.jads.myfinancesbackend.app.access.interceptor.ResourceActionInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@AllArgsConstructor
@Configuration
public class WebAccessConfig implements WebMvcConfigurer {

    private final ResourceActionInterceptor resourceActionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(resourceActionInterceptor);
    }

}
