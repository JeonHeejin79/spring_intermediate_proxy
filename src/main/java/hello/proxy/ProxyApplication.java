package hello.proxy;

import hello.proxy.config.v6_aop.AopConfig;
import hello.proxy.trace.logtrace.LogTrace;
import hello.proxy.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

// @Import(AppV1Config.class) // AppV1Config 을 스프링빈에 등록된다.
// @Import({AppV1Config.class, AppV2Config.class}) // AppV2Config 을 스프링빈에 등록된다.
// @Import(InterfaceProxyConfig.class) // AppV2Config 을 스프링빈에 등록된다.
// @Import(ConcreteProxyConfig.class) // AppV2Config 을 스프링빈에 등록된다.
// @Import(DynamicProxyBasicConfig.class) // AppV2Config 을 스프링빈에 등록된다.
// @Import(DynamicProxyFilterConfig.class) // AppV2Config 을 스프링빈에 등록된다.
// @Import(ProxyFactoryConfigV1.class) // ProxyFactoryConfigV1 을 스프링빈에 등록된다.
// @Import(ProxyFactoryConfigV2.class) // ProxyFactoryConfigV2 을 스프링빈에 등록된다.
// @Import(BeanPostProcessorConfig.class) // BeanPostProcessorConfig 을 스프링빈에 등록된다.
//@Import(AutoProxyConfig.class) // AutoProxyConfig 을 스프링빈에 등록된다.
@Import(AopConfig.class) // AutoProxyConfig 을 스프링빈에 등록된다.
@SpringBootApplication(scanBasePackages = "hello.proxy.app") // !주의 app 하위 에있는것만 스캔대상이 됨
public class ProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProxyApplication.class, args);
	}

	@Bean
	public LogTrace logTrace() {
		return new ThreadLocalLogTrace();
	}
}
