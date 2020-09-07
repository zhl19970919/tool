package pren.zhl.tool.service.impl;

import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import pren.zhl.tool.entity.Media;
import pren.zhl.tool.mapper.MediaMapper;
import pren.zhl.tool.service.IMediaService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhl
 * @since 2020-09-07
 */
@Service
public class MediaServiceImpl extends ServiceImpl<MediaMapper, Media> implements IMediaService {

    public Boolean insertMedia(Media media, MultipartFile file){
        String originName = file.getOriginalFilename();
        String mediaType = originName.substring(originName.lastIndexOf(".")+1);
        String currname = System.currentTimeMillis()+"."+mediaType;
        if (StringUtils.isEmpty(mediaType)){
            log.error("文件没有后缀");
            return false;
        }
        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath()+"static"+ File.separator+"upload";
        path = path + File.separator+mediaType+File.separator+currname;
        File dest = new File(path);
        media.setOriginName(originName);
        media.setMediaType(mediaType);
        media.setMediaUrl(path);
        media.setCurrName(currname);
        if (!dest.getParentFile().exists()){
            dest.mkdirs();
        }
        try {
            file.transferTo(dest);
            baseMapper.insert(media);
            return true;
        }catch (IOException e){
            log.error("文件传输报错",e);
        }catch (IllegalStateException e){
            log.error("加载文件异常",e);
        }
        return false;
    }

}
