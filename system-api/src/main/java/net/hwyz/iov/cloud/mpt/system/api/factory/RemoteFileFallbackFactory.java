package net.hwyz.iov.cloud.mpt.system.api.factory;

import net.hwyz.iov.cloud.framework.common.bean.Response;
import net.hwyz.iov.cloud.mpt.system.api.RemoteFileService;
import net.hwyz.iov.cloud.mpt.system.api.domain.SysFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件服务降级处理
 *
 * @author hwyz_leo
 */
@Component
public class RemoteFileFallbackFactory implements FallbackFactory<RemoteFileService> {
    private static final Logger log = LoggerFactory.getLogger(RemoteFileFallbackFactory.class);

    @Override
    public RemoteFileService create(Throwable throwable) {
        log.error("文件服务调用失败:{}", throwable.getMessage());
        return new RemoteFileService() {
            @Override
            public Response<SysFile> upload(MultipartFile file) {
                return Response.fail("上传文件失败:" + throwable.getMessage());
            }
        };
    }
}
