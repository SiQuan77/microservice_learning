package cn.itcast.gateway;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Order(-1) //这个值越小，过滤器优先级越高
@Component
public class AuthorizeFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //1.获取请求参数
        MultiValueMap<String, String> params = exchange.getRequest().getQueryParams();
        //2.获取参数中的authorization参数
        String auth = params.getFirst("authorization");
        //3.判断参数值是否等于admin
        if ("admin".equals(auth)) {
            //4.是、放行
            return chain.filter(exchange);
            //5.否，拦截
        }
        exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
        return exchange.getResponse().setComplete();
    }
}
