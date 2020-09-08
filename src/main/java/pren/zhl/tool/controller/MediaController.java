package pren.zhl.tool.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pren.zhl.tool.bean.Response;
import pren.zhl.tool.entity.Media;
import pren.zhl.tool.service.IMediaService;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhl
 * @since 2020-09-07
 */
@RestController
@RequestMapping("/tool/media")
@Slf4j
@Api(value="媒体类controller",tags={"媒体类接口"})
public class MediaController {
    @Resource
    private Response response;

    @Resource
    private IMediaService iMediaService;

    @ApiOperation(value = "上传单个文件")
    @PostMapping("/upload")
    public Response upload(Media media, @RequestParam(value="file", required=false) MultipartFile file){
        if (file.isEmpty()){
            return response.failure("文件不存在");
        }
        if (iMediaService.insertMedia(media,file))
            return response.success("上传成功");
        return response.failure("上传失败");
    }
}
