/**
 * Copyright (c) 2016-2019 NBA All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package io.renren.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.exception.RRException;
import io.renren.common.utils.Constant;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.sys.dao.SysTbUserDao;
import io.renren.modules.sys.dao.SysUserDao;
import io.renren.modules.sys.entity.SysTbUserEntity;
import io.renren.modules.sys.service.SysRoleService;
import io.renren.modules.sys.service.SysTbUserService;
import io.renren.modules.sys.service.SysUserRoleService;
import io.renren.modules.sys.service.SysUserService;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 系统用户
 *
 * @author Mark sunlightcs@gmail.com
 */
@Service("sysTbUserService")
public class SysTbUserServiceImpl extends ServiceImpl<SysTbUserDao, SysTbUserEntity> implements SysTbUserService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String account = (String) params.get("account");

        IPage<SysTbUserEntity> page = this.page(
                new Query<SysTbUserEntity>().getPage(params),
                new QueryWrapper<SysTbUserEntity>()
                        .like(StringUtils.isNotBlank(account), "account", account)
        );

        return new PageUtils(page);
    }

    @Override
    public void saveUser(SysTbUserEntity user) {
        user.setCreateTime(new Date());
        baseMapper.save(user);

    }

    @Override
    public void update(SysTbUserEntity user) {
        user.setCreateTime(new Date());
        baseMapper.edit(user);
    }


    @Override
    public void deleteBatch(Long[] userId) {
        this.removeByIds(Arrays.asList(userId));
    }

    @Override
    public SysTbUserEntity selectOne(Integer userId) {
        return this.baseMapper.selectOne(new QueryWrapper<SysTbUserEntity>().eq("user_id", userId));
    }

}
