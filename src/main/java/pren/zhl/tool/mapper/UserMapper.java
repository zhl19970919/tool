package pren.zhl.tool.mapper;

import pren.zhl.tool.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author zhl
 * @since 2020-08-27
 */
public interface UserMapper extends BaseMapper<User> {

    Long getNextId();
}
