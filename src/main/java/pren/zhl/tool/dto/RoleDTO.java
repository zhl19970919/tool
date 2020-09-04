package pren.zhl.tool.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="RoleDTO", description="角色")

public class RoleDTO implements Serializable {
    @ApiModelProperty(value = "所属父级角色ID")
    private Long parentId;

    @ApiModelProperty(value = "角色唯一CODE代码")
    private String code;

    @ApiModelProperty(value = "角色名称")
    private String name;

    @ApiModelProperty(value = "角色介绍")
    private String intro;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "角色ID")
    private Long roleId;



}
