package com.springcloud.zuul.provider;

import com.netflix.hystrix.exception.HystrixTimeoutException;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class WebFallbackProvider implements FallbackProvider {
    @Override
    public String getRoute() {
        //制定为那个服务提供回退(这里写微服务的名字 ,写*代表所有)
        return "*";
    }
    /**
     *   此方法需要返回一个ClientHttpResponse对象
     *    ClientHttpResponse是一个接口，具体的回退逻辑要实现此接口
     *    route:出错的微服务名 cause：出错的异常对象
     */
    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        //这里可以判断根据 不同的异常来做不同的处理，也可以不判断
        //完了之后调用response方法并根据异常类型传入HttpStatus
        if(cause instanceof HystrixTimeoutException){
            return response(HttpStatus.GATEWAY_TIMEOUT);
        }else{
            return response(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    private ClientHttpResponse response(final HttpStatus status) {
        //这里返回一个ClientHttpResponse对象 并实现其中的方法，关于回退逻辑的，便在下面的方法中
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                //返回一个HttpStatus对象
                return status;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return status.value();
            }

            @Override
            public String getStatusText() throws IOException {
                return status.getReasonPhrase();
            }

            @Override
            public void close() {
                //关闭的时候调用
            }

            @Override
            public InputStream getBody() throws IOException {
                return new ByteArrayInputStream("降级信息".getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                //需要对响应报头设置的话可以在此设置
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }
        };
    }
}
