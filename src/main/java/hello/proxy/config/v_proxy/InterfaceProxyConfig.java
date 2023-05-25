package hello.proxy.config.v_proxy;

import hello.proxy.app.v1.*;
import hello.proxy.config.v_proxy.interface_proxy.OrderControllerInterfaceProxy;
import hello.proxy.config.v_proxy.interface_proxy.OrderRepositoryInterfaceProxy;
import hello.proxy.config.v_proxy.interface_proxy.OrderServiceInterfaceProxy;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 프록시를 생성한다. -> 스프링 빈에 프록시가 등록된다.
// 1. 프록시를 스프링 빈에 등록하고
// 2. 프록시가 서비스 Impl 을 호출하고
// 3. 서비스 Impl 은 또 프록시를 호출한다.
// -> 따라서 프록시를 생성하고 프록시를 실체 스프링 빈 대신 등록한다.
// -> 실제 객체틑 스프링 빈으로 등록하지 않는다.
// -> 프록시는 내부 실체 객체를 참조하고 있다.
// -> 실제 객체 대신에 프록시 객체를 등록 했기떄문에 앞으로 스프링 빈을 주입받으면 실제 객체 대신 프록시 객체가 주입된다.
// -> 프록시 객체를 통해서 실제 객체를 호출할 수 있다.

@Configuration
public class InterfaceProxyConfig {

    @Bean
    public OrderControllerV1 orderController(LogTrace logTrace) {
        // 구현체는 프록시를 반환한다.
        OrderControllerV1Impl controllerImpl = new OrderControllerV1Impl(orderService(logTrace));
        return new OrderControllerInterfaceProxy(controllerImpl, logTrace);
    }

    // 프록시를 생성한다.
    @Bean
    public OrderServiceV1 orderService(LogTrace logTrace) {
        // 구현체는 프록시를 반환한다.
        OrderServiceV1Impl serviceImpl = new OrderServiceV1Impl(orderRepository(logTrace));
        return new OrderServiceInterfaceProxy(serviceImpl, logTrace);
    }

    @Bean
    public OrderRepositoryV1 orderRepository(LogTrace logTrace) {
        OrderRepositoryV1Impl repositoryImpl = new OrderRepositoryV1Impl();
        return new OrderRepositoryInterfaceProxy(repositoryImpl, logTrace);
    }
}
