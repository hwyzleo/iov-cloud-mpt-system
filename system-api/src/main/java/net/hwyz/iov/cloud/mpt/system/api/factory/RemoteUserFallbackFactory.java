package net.hwyz.iov.cloud.mpt.system.api.factory;

import net.hwyz.iov.cloud.framework.common.bean.Response;
import net.hwyz.iov.cloud.mpt.system.api.RemoteUserService;
import net.hwyz.iov.cloud.mpt.system.api.domain.SysUser;
import net.hwyz.iov.cloud.mpt.system.api.model.LoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 用户服务降级处理
 *
 * @author hwyz_leo
 */
@Component
public class RemoteUserFallbackFactory implements FallbackFactory<RemoteUserService> {
    private static final Logger log = LoggerFactory.getLogger(RemoteUserFallbackFactory.class);

    @Override
    public RemoteUserService create(Throwable throwable) {
        log.error("用户服务调用失败:{}", throwable.getMessage());
        return new RemoteUserService() {
            @Override
            public Response<LoginUser> getUserInfo(String username, String source) {
                return Response.fail("获取用户失败:" + throwable.getMessage());
            }

            @Override
            public Response<Boolean> registerUserInfo(SysUser sysUser, String source) {
                return Response.fail("注册用户失败:" + throwable.getMessage());
            }

            @Override
            public Response<Boolean> recordUserLogin(SysUser sysUser, String source) {
                return Response.fail("记录用户登录信息失败:" + throwable.getMessage());
            }
        };
    }
}
