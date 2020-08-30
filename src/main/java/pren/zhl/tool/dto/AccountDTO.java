package pren.zhl.tool.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="AccountDTO", description="账号")
public class AccountDTO {
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


    /**
     * 密码盐. 重新对盐重新进行了定义，用户名+salt，这样就不容易被破解，可以采用多种方式定义加盐
     *
     * @return
     */
    public String getCredentialsSalt() {
        return this.name + this.salt;
    }
}
