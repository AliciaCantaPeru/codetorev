package marketplace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@ComponentScan
@EnableAsync
public class BmoiMarketplaceWsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BmoiMarketplaceWsApplication.class, args);
    }

}
