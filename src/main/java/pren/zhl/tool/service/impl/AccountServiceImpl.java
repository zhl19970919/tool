package pren.zhl.tool.service.impl;

import pren.zhl.tool.entity.Account;
import pren.zhl.tool.mapper.AccountMapper;
import pren.zhl.tool.service.IAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 账号 服务实现类
 * </p>
 *
 * @author zhl
 * @since 2020-08-27
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {

}
