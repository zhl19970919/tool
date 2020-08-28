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

/**
 * <p>
 * 账号
 * </p>
 *
 * @author zhl
 * @since 2020-08-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tool_account")
@ApiModel(value="Account对象", description="账号")
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "账号ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "登录账号,如手机号等")
    private String openCode;

    @ApiModelProperty(value = "账号类别")
    private Boolean category;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime created;

    @ApiModelProperty(value = "创建人")
    private String creator;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime edited;

    @ApiModelProperty(value = "修改人")
    private String editor;

    @ApiModelProperty(value = "逻辑删除:0=未删除,1=已删除")
    private Double deleted;


}
