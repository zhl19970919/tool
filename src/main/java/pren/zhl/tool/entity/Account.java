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

    @ApiModelProperty(value = "登录账号,如手机号等\n" +
            "1.普通账号\n" +
            "2.手机号\n" +
            "3.邮箱\n" +
            "4.其他")
    private String openCode;

    @ApiModelProperty(value = "账号类别")
    private Integer category;

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
