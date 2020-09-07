package pren.zhl.tool.service;

import org.springframework.web.multipart.MultipartFile;
import pren.zhl.tool.entity.Media;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhl
 * @since 2020-09-07
 */
public interface IMediaService extends IService<Media> {

    /**
     *上传文件
     * @param file
     * @return
     */
    Boolean insertMedia(Media media,MultipartFile file);
}
