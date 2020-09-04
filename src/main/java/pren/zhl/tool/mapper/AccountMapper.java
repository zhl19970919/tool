package pren.zhl.tool.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import pren.zhl.tool.dto.AccountDTO;
import pren.zhl.tool.entity.Account;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 账号 Mapper 接口
 * </p>
 *
 * @author zhl
 * @since 2020-08-27
 */
public interface AccountMapper extends BaseMapper<Account> {

    AccountDTO findByOpencode(@Param("openCode") String username, @Param("userDeleted") String userDeleted, @Param("userId") String userId);

    Page<AccountDTO> getAccountList(Page<AccountDTO> page);

}
