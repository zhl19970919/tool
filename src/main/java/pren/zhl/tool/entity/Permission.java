package pren.zhl.tool.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 权限
 * </p>
 *
 * @author zhl
 * @since 2020-08-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tool_permission")
@ApiModel(value="Permission对象", description="权限")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "权限ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "所属父级权限ID")
    private Long parentId;

    @ApiModelProperty(value = "权限唯一CODE代码")
    private String code;

    @ApiModelProperty(value = "权限名称")
    private String name;

    @ApiModelProperty(value = "权限介绍")
    private String intro;

    @ApiModelProperty(value = "权限类别")
    private Integer category;

    @ApiModelProperty(value = "URL规则")
    private Long uri;

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

    private List<Permission> subAppMenu;
}
