package pren.zhl.tool.service;

import org.springframework.context.annotation.Primary;
import pren.zhl.tool.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author zhl
 * @since 2020-08-27
 */
@Primary
public interface IUserService extends IService<User> {

    List<User> listUsers();
}

