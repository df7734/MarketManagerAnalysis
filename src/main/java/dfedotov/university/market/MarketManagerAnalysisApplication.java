package dfedotov.university.market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MarketManagerAnalysisApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarketManagerAnalysisApplication.class, args);
    }

}
