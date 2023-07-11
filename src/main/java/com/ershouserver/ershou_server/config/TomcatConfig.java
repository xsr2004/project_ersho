package com.ershouserver.ershou_server.config;
import org.apache.catalina.connector.Connector;

import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

/**
 * @Author：xsr
 * @name：TomcatConfig
 * @Date：2023/5/11 17:31
 * @Filename：TomcatConfig
 */
@Configuration
public class TomcatConfig {

//    @Bean
//    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> tomcatCustomizer() {
//        return new WebServerFactoryCustomizer<TomcatServletWebServerFactory>() {
//            @Override
//            public void customize(TomcatServletWebServerFactory factory) {
//                factory.addConnectorCustomizers(new TomcatConnectorCustomizer[]{}() {
//                    @Override
//                    public void customize(Connector) {
//                        final int maxSize = 1024 * 1024 * 50; // 设置最大文件上传大小为50MB
//                        connector.setMaxPostSize(maxSize);
//                        connector.setMaxSavePostSize(maxSize);
//                    }
//                });
//            }
//        };
//    }
}

