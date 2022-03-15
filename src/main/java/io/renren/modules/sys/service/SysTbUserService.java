/**
 * Copyright (c) 2016-2019 NBA All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.SysTbUserEntity;

import java.util.List;
import java.util.Map;


/**
 * 系统用户
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface SysTbUserService extends IService<SysTbUserEntity> {

	PageUtils queryPage(Map<String, Object> params);

	/**
	 * 保存用户
	 */
	void saveUser(SysTbUserEntity user);

	/**
	 * 修改用户
	 */
	void update(SysTbUserEntity user);

	/**
	 * 删除用户
	 */
	void deleteBatch(Long[] userIds);

	SysTbUserEntity selectOne(Integer userId);
}
