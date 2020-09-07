package pren.zhl.tool.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhl
 * @since 2020-09-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tool_media")
@ApiModel(value="Media对象", description="")
public class Media implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "媒体类ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "名称")
    private String mediaName;

    @ApiModelProperty(value = "源文件名")
    private String originName;

    @ApiModelProperty(value = "当前文件名")
    private String currName;

    @ApiModelProperty(value = "媒体类型")
    private String mediaType;

    @ApiModelProperty(value = "储存网址")
    private String mediaUrl;

    @ApiModelProperty(value = "操作代码")
    private Integer operateCode;

    @ApiModelProperty(value = "处理结果-文字")
    private String resultWord;

    @ApiModelProperty(value = "处理结果-媒体URL")
    private String resultMediaUrl;

    @ApiModelProperty(value = "逻辑删除:0=未删除,1=已删除")
    private Boolean deleted;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime created;

    @ApiModelProperty(value = "创建人")
    private String creator;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime edited;

    @ApiModelProperty(value = "修改人")
    private String editor;

}
