package pren.zhl.tool.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pren.zhl.tool.entity.Account;
import springfox.documentation.annotations.ApiIgnore;

import java.awt.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="AccountDTO", description="账号")
public class AccountDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "账号ID")
    private Long id;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "角色ID")
    private Long[] roleIds;

    @ApiModelProperty(value = "登录账号,如手机号等")
    private String openCode;

    @ApiModelProperty(value = "账号类别")
    private Integer category;

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

    @ApiModelProperty(value = "账号组")
    private ArrayList<Account> Opencodes;

    @ApiModelProperty(value = "账号Row")
    private String usernames;

    @ApiModelProperty(value = "idRow")
    private String ids;

}
