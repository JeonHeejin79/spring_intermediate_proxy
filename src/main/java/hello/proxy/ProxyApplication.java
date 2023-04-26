package hello.proxy;

import hello.proxy.config.AppV1Config;
import hello.proxy.config.AppV2Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

// @Import(AppV1Config.class) // AppV1Config 을 스프링빈에 등록된다.
@Import({AppV1Config.class, AppV2Config.class}) // AppV2Config 을 스프링빈에 등록된다.
@SpringBootApplication(scanBasePackages = "hello.proxy.app") // !주의 app 하위 에있는것만 스캔대상이 됨
public class ProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProxyApplication.class, args);
	}

}
