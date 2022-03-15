/**
 * Copyright (c) 2016-2019 NBA All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.modules.sys.controller;

import io.renren.common.annotation.SysLog;
import io.renren.common.utils.Constant;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.validator.ValidatorUtils;
import io.renren.common.validator.group.AddGroup;
import io.renren.common.validator.group.UpdateGroup;
import io.renren.modules.sys.entity.SysTbUserEntity;
import io.renren.modules.sys.service.SysTbUserService;
import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 系统用户
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("/sys/admin")
public class SysTbUserController extends AbstractController {

	@Autowired
	private SysTbUserService sysTbUserService;


	/**
	 * 所有用户列表
	 */
	@GetMapping("/list")
//	@RequiresPermissions("sys:admin:list")
	public R list(@RequestParam Map<String, Object> params){
		//只有超级管理员，才能查看所有管理员列表
		if(getUserId() != Constant.SUPER_ADMIN){
			params.put("createUserId", getUserId());
		}
		PageUtils page = sysTbUserService.queryPage(params);

		return R.ok().put("page", page);
	}



	/**
	 * 保存用户
	 */
	@SysLog("保存用户")
	@PostMapping("/save")
//	@RequiresPermissions("sys:admin:save")
	public R save(@RequestBody SysTbUserEntity user){
		ValidatorUtils.validateEntity(user, AddGroup.class);

		sysTbUserService.saveUser(user);

		return R.ok();
	}

	/**
	 * 修改用户
	 */
	@SysLog("修改用户")
	@PostMapping("/update")
//	@RequiresPermissions("sys:admin:update")
	public R update(@RequestBody SysTbUserEntity user){
		ValidatorUtils.validateEntity(user, UpdateGroup.class);

		sysTbUserService.update(user);

		return R.ok();
	}

	/**
	 * 删除用户
	 */
	@SysLog("删除用户")
	@PostMapping("/delete")
//	@RequiresPermissions("sys:admin:delete")
	public R delete(@RequestBody Long[] userIds){
		if(ArrayUtils.contains(userIds, 1L)){
			return R.error("系统管理员不能删除");
		}

		if(ArrayUtils.contains(userIds, getUserId())){
			return R.error("当前用户不能删除");
		}

		sysTbUserService.deleteBatch(userIds);

		return R.ok();
	}
	/**
	 * 删除用户
	 */
	@SysLog("单个用户")
	@GetMapping("/info/{userId}")
//	@RequiresPermissions("sys:admin:delete")
	public R selectOne(@PathVariable("userId") Integer userId){

		SysTbUserEntity user = sysTbUserService.selectOne(userId);

		return R.ok().put("user",user);
	}
}
