package pren.zhl.tool.service;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.Transactional;
import pren.zhl.tool.bean.CacheUser;
import pren.zhl.tool.dto.AccountDTO;
import pren.zhl.tool.entity.Account;
import com.baomidou.mybatisplus.extension.service.IService;
import pren.zhl.tool.entity.User;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 账号 服务类
 * </p>
 *
 * @author zhl
 * @since 2020-08-27
 */

public interface IAccountService extends IService<Account> {

    /**
     * create by: leigq
     * description: 根据用户名获取用户
     * create time: 2019/6/28 16:19
     * @param userName 用户名
     * @return 用户
     */
    Account findByUsername(String userName);

    AccountDTO findByOpencode(String username, String userDeleted, String userId);

    /**
     * 新增用户
     * @param accountDTO
     * @return
     */
    Integer register(AccountDTO accountDTO);


    /**
     * 删除用户（deleted 设为1）
     * @param userId
     * @return
     */
    Boolean delete(Long userId);

    /**
     *  恢复用户(deleted 设为0)
     * @param userId
     * @return
     */
    Boolean recover(Long userId);

    /**
     * create by: leigq
     * description: 登录
     * create time: 2019/6/28 16:26
     * @param userName 用户名
     * @param password 密码
     * @return 用户信息
     */

    /**
     * 用户主页
     * @return
     */
    List<AccountDTO> getAccountList();

    CacheUser login(String userName, String password);


    /**
     * create by: leigq
     * description: 登出
     * create time: 2019/6/28 16:30
     */
    void logout();

    List<Account> listAccounts();

}
