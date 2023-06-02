package hello.proxy.jdkdynamic;

import hello.proxy.jdkdynamic.code.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

@Slf4j
public class JdkDynamicProxyTest {

    @Test
    void dynamicA() {
        AInterface target = new AImpl();

        TimeInvocationHandler handler = new TimeInvocationHandler(target);

        // newProxyInstance(어느 클래스로더에할지 지정,
        // 어떤 인터페이스기반으로 프록시를 만들지, 프록시가 사용해야될 로직이 뭔지);
        // -> 동적으로 프록시 객체가 생성이 된다.
        AInterface proxy = (AInterface) Proxy.newProxyInstance(
                AInterface.class.getClassLoader(),
                new Class[]{AInterface.class}, handler);

        proxy.call();

        log.info("targetClass = {}", target.getClass());
        // targetClass = class hello.proxy.jdkdynamic.code.AImpl : 실제클래스
        log.info("proxyClass = {}", proxy.getClass());
        // proxyClass = class com.sun.proxy.$Proxy12 : 크록시가 만든 프록시 클래스,
        // AInterface 를 구현받아서 만든 클래스 이다.
    }

    @Test
    void dynamicB() {
        BInterface target = new BImpl();

        TimeInvocationHandler handler = new TimeInvocationHandler(target);

        // newProxyInstance(어느 클래스로더에할지 지정,
        // 어떤 인터페이스기반으로 프록시를 만들지, 프록시가 사용해야될 로직이 뭔지);
        // -> 동적으로 프록시 객체가 생성이 된다.
        BInterface proxy = (BInterface) Proxy.newProxyInstance(
                BInterface.class.getClassLoader(),
                new Class[]{BInterface.class}, handler);

        proxy.call();

        log.info("targetClass = {}", target.getClass());
        // targetClass = class hello.proxy.jdkdynamic.code.AImpl : 실제클래스
        log.info("proxyClass = {}", proxy.getClass());
        // proxyClass = class com.sun.proxy.$Proxy12 : 크록시가 만든 프록시 클래스, AInterface 를 구현받아서 만든 클래스 이다.
    }
}
