package net.hwyz.iov.cloud.mpt.system.service;

import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.framework.security.annotation.EnableCustomConfig;
import net.hwyz.iov.cloud.framework.security.annotation.EnableCustomFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 系统模块
 *
 * @author hwyz_leo
 */
@Slf4j
@EnableCustomConfig
@EnableCustomFeignClients
@SpringBootApplication
public class SystemApplication {
    public static void main(String[] args) {
        System.setProperty("nacos.logging.default.config.enabled", "false");
        SpringApplication.run(SystemApplication.class, args);
        logger.info("应用启动成功");
    }
}
