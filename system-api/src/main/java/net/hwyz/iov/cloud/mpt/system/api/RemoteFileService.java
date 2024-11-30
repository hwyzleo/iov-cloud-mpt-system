package net.hwyz.iov.cloud.mpt.system.api;

import net.hwyz.iov.cloud.framework.common.bean.Response;
import net.hwyz.iov.cloud.mpt.system.api.domain.SysFile;
import net.hwyz.iov.cloud.mpt.system.api.factory.RemoteFileFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件服务
 *
 * @author hwyz_leo
 */
@FeignClient(contextId = "remoteFileService", value = "mpt-file", fallbackFactory = RemoteFileFallbackFactory.class)
public interface RemoteFileService {
    /**
     * 上传文件
     *
     * @param file 文件信息
     * @return 结果
     */
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Response<SysFile> upload(@RequestPart(value = "file") MultipartFile file);
}
