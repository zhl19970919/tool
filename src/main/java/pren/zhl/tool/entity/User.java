package pren.zhl.tool.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * 用户
 * </p>
 *
 * @author zhl
 * @since 2020-08-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tool_user")
@ApiModel(value="User对象", description="用户")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID")
    //@TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户状态:0=正常,1=禁用")
    private Boolean state;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "头像图片地址")
    private String headImgUrl;

    @ApiModelProperty(value = "手机号码")
    private String mobile;

    @ApiModelProperty(value = "密码加盐")
    private String salt;

    @ApiModelProperty(value = "登录密码")
    private String password;

    @ApiModelProperty(value = "创建时间")
    @TableField( fill = FieldFill.INSERT)
    private LocalDateTime created;

    @ApiModelProperty(value = "创建人")
    @TableField( fill = FieldFill.INSERT)
    private String creator;

    @ApiModelProperty(value = "修改时间")
    @TableField( fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime edited;

    @ApiModelProperty(value = "修改人")
    @TableField( fill = FieldFill.INSERT_UPDATE)
    private String editor;

    @ApiModelProperty(value = "逻辑删除:0=未删除,1=已删除")
    private Boolean deleted;

}
