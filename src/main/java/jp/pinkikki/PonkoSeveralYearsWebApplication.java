package jp.pinkikki;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@SpringBootApplication
@EntityScan(basePackageClasses = {PonkoSeveralYearsWebApplication.class, Jsr310JpaConverters.class})
public class PonkoSeveralYearsWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(PonkoSeveralYearsWebApplication.class, args);
    }
}
