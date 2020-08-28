package pren.zhl.tool.service;

import org.springframework.context.annotation.Primary;
import pren.zhl.tool.entity.Account;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 账号 服务类
 * </p>
 *
 * @author zhl
 * @since 2020-08-27
 */
@Primary
public interface IAccountService extends IService<Account> {

}
