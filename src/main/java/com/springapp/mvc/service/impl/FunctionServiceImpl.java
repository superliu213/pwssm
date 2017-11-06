package com.springapp.mvc.service.impl;

import com.springapp.common.exceptions.ApplicationException;
import com.springapp.common.page.PageBean;
import com.springapp.common.page.PageParam;
import com.springapp.common.service.BaseServiceImpl;
import com.springapp.mvc.dao.FunctionDao;
import com.springapp.mvc.dao.RoleFunctionDao;
import com.springapp.mvc.entity.Function;
import com.springapp.mvc.service.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("functionService")
@Transactional
public class FunctionServiceImpl extends BaseServiceImpl implements FunctionService {

	@Autowired
	FunctionDao functionDao;

	@Autowired
	RoleFunctionDao roleFunctionDao;

	@Override
	public PageBean getFunctionPageBean(PageParam pageParam, Map<String, Object> paramMap) {
		PageBean pageBean = functionDao.listPage(pageParam, paramMap);
		return pageBean;
	}

	@Override
	public List<Function> getAllFunctions() {
		List<Function> result = functionDao.listBy(null);
		return result;
	}

	@Override
	public void removeFunctionByKey(Long id) {
		List functionUsed = functionDao.getFunctionUsed(id);
		if(functionUsed.size() > 0){
			throw new ApplicationException("此functionId已被引用，无法删除");
		}else{
			Function function = functionDao.getById(id);
			String functionId = function.getFunctionId();
			roleFunctionDao.deleteByFunctionId(functionId);
			functionDao.deleteById(id);
		}
	}

	@Override
	public void addFunction(Function function) {
		functionDao.insert(function);
	}

	@Override
	public void updateFunction(Function function) {
		functionDao.update(function);
	}

	@Override
	public void initData() {
		Function function1 = new Function(1L, "1", "权限管理", (short) 1, "-1", "#", 1, "fa-diamond", null, "1");
		Function function2 = new Function(2L, "2", "报表管理", (short) 1, "-1", "#", 2, "fa-bar-chart", null, "1");
		Function function3 = new Function(3L, "3", "系统管理", (short) 1, "-1", "#", 3, "fa-money", null, "1");
		Function function4 = new Function(4L, "4", "日志管理", (short) 1, "-1", "#", 4, "fa-map-o", null, "1");

		Function function5 = new Function(5L, "5", "用户管理", (short) 2, "1", "./modules/authority/user/user.html",
				101, "", null, "1");
		Function function6 = new Function(6L, "6", "角色管理", (short) 2, "1", "./modules/authority/role/role.html",
				102, "", null, "1");
		Function function7 = new Function(7L, "7", "机构管理", (short) 2, "1", "./modules/authority/group/group.html",
				103, "", null, "1");
		Function function8 = new Function(8L, "8", "权限管理", (short) 2, "1",
				"./modules/authority/function/function.html", 104, "", null, "1");

		Function function9 = new Function(9L, "9", "demo", (short) 2, "2", "./modules/report/demo/report.html", 201,
				"", null, "1");

		Function function10 = new Function(10L, "10", "性能监控", (short) 2, "3", "/monitoring", 301, "", null, "1");
		Function function11 = new Function(11L, "11", "数据库监控", (short) 2, "3", "/proxooladmin", 302, "", null, "1");

		Function function12 = new Function(12L, "12", "日志查询", (short) 2, "4", "./modules/log/log/log.html", 401, "",
				null, "1");

		Function function13 = new Function(13L, "13", "添加", (short) 3, "5", "", 501, "", "userForm.add_table", "1");
		Function function14 = new Function(14L, "14", "编辑", (short) 3, "5", "", 502, "", "userForm.update_table",
				"1");
		Function function15 = new Function(15L, "15", "删除", (short) 3, "5", "", 503, "", "userForm.remove_table",
				"1");
		Function function16 = new Function(16L, "16", "密码重置", (short) 3, "5", "", 504, "",
				"userForm.password_reset", "1");
		Function function17 = new Function(17L, "17", "导入", (short) 3, "5", "", 505, "", "userForm.import_table",
				"1");
		Function function18 = new Function(18L, "18", "导出", (short) 3, "5", "", 506, "", "userForm.export_table",
				"1");
		Function function19 = new Function(19L, "19", "配置机构", (short) 3, "5", "", 507, "",
				"userForm.configure_group", "1");
		Function function20 = new Function(20L, "20", "配置角色", (short) 3, "5", "", 508, "",
				"userForm.configure_role", "1");
		Function function21 = new Function(21L, "21", "查询", (short) 3, "5", "", 509, "", "userForm.query_table",
				"1");
		Function function22 = new Function(22L, "22", "重置", (short) 3, "5", "", 510, "", "userForm.reset_form",
				"1");
		Function function23 = new Function(23L, "23", "数据初始化", (short) 3, "5", "", 511, "", "userForm.reset_data",
				"1");

		Function function24 = new Function(24L, "24", "添加", (short) 3, "6", "", 601, "", "roleForm.add_table", "1");
		Function function25 = new Function(25L, "25", "编辑", (short) 3, "6", "", 602, "", "roleForm.update_table",
				"1");
		Function function26 = new Function(26L, "26", "删除", (short) 3, "6", "", 603, "", "roleForm.remove_table",
				"1");
		Function function27 = new Function(27L, "27", "导入", (short) 3, "6", "", 604, "", "roleForm.import_table",
				"1");
		Function function28 = new Function(28L, "28", "导出", (short) 3, "6", "", 604, "", "roleForm.export_table",
				"1");
		Function function29 = new Function(29L, "29", "配置权限", (short) 3, "6", "", 605, "",
				"roleForm.configure_function", "1");
		Function function30 = new Function(30L, "30", "查询", (short) 3, "6", "", 606, "", "roleForm.query_table",
				"1");
		Function function31 = new Function(31L, "31", "重置", (short) 3, "6", "", 607, "", "roleForm.reset_form",
				"1");
		Function function32 = new Function(32L, "32", "数据初始化", (short) 3, "6", "", 608, "", "roleForm.reset_data",
				"1");

		Function function33 = new Function(33L, "33", "添加", (short) 3, "7", "", 701, "", "groupForm.add_table",
				"1");
		Function function34 = new Function(34L, "34", "编辑", (short) 3, "7", "", 702, "", "groupForm.update_table",
				"1");
		Function function35 = new Function(35L, "35", "删除", (short) 3, "7", "", 703, "", "groupForm.remove_table",
				"1");
		Function function36 = new Function(36L, "36", "配置角色", (short) 3, "7", "", 704, "",
				"groupForm.configure_function", "1");
		Function function37 = new Function(37L, "37", "查询", (short) 3, "7", "", 705, "", "groupForm.query_table",
				"1");
		Function function38 = new Function(38L, "38", "重置", (short) 3, "7", "", 706, "", "groupForm.reset_form",
				"1");
		Function function39 = new Function(39L, "39", "数据初始化", (short) 3, "7", "", 707, "", "groupForm.reset_data",
				"1");

		Function function40 = new Function(40L, "40", "添加", (short) 3, "8", "", 801, "", "functionForm.add_table",
				"1");
		Function function41 = new Function(41L, "41", "编辑", (short) 3, "8", "", 802, "",
				"functionForm.update_table", "1");
		Function function42 = new Function(42L, "42", "删除", (short) 3, "8", "", 803, "",
				"functionForm.remove_table", "1");
		Function function43 = new Function(43L, "43", "查询", (short) 3, "8", "", 804, "", "functionForm.query_table",
				"1");
		Function function44 = new Function(44L, "44", "重置", (short) 3, "8", "", 805, "", "functionForm.reset_form",
				"1");
		Function function45 = new Function(45L, "45", "数据初始化", (short) 3, "8", "", 806, "",
				"functionForm.reset_data", "1");

		Function function46 = new Function(46L, "46", "查询", (short) 3, "9", "", 901, "", "reportForm.query_table",
				"1");
		Function function47 = new Function(47L, "47", "重置", (short) 3, "9", "", 902, "", "reportForm.reset_form",
				"1");

		Function function48 = new Function(48L, "48", "查询", (short) 3, "12", "", 1201, "", "logForm.query_table",
				"1");
		Function function49 = new Function(49L, "49", "重置", (short) 3, "12", "", 1203, "", "logForm.reset_form",
				"1");
		Function function50 = new Function(50L, "50", "数据初始化", (short) 3, "12", "", 1203, "", "logForm.reset_data",
				"1");

		List<Function> functions = functionDao.listBy(null);
		for (Function function : functions) {
			functionDao.deleteById(function.getId());
		}

		functionDao.insert(function1);
		functionDao.insert(function2);
		functionDao.insert(function3);
		functionDao.insert(function4);
		functionDao.insert(function5);
		functionDao.insert(function6);
		functionDao.insert(function7);
		functionDao.insert(function8);
		functionDao.insert(function9);
		functionDao.insert(function10);
		functionDao.insert(function11);
		functionDao.insert(function12);
		functionDao.insert(function13);
		functionDao.insert(function14);
		functionDao.insert(function15);
		functionDao.insert(function16);
		functionDao.insert(function17);
		functionDao.insert(function18);
		functionDao.insert(function19);
		functionDao.insert(function20);
		functionDao.insert(function21);
		functionDao.insert(function22);
		functionDao.insert(function23);
		functionDao.insert(function24);
		functionDao.insert(function25);
		functionDao.insert(function26);
		functionDao.insert(function27);
		functionDao.insert(function28);
		functionDao.insert(function29);
		functionDao.insert(function30);
		functionDao.insert(function31);
		functionDao.insert(function32);
		functionDao.insert(function33);
		functionDao.insert(function34);
		functionDao.insert(function35);
		functionDao.insert(function36);
		functionDao.insert(function37);
		functionDao.insert(function38);
		functionDao.insert(function39);
		functionDao.insert(function40);
		functionDao.insert(function41);
		functionDao.insert(function42);
		functionDao.insert(function43);
		functionDao.insert(function44);
		functionDao.insert(function45);
		functionDao.insert(function46);
		functionDao.insert(function47);
		functionDao.insert(function48);
		functionDao.insert(function49);
		functionDao.insert(function50);

	}

	public List<String> getButtonPosition(String formName, String userId) {
		List<String> result = new ArrayList<>();
		Map<String, Object> paramMap = null;

		if (!"admin".equals(userId)) {
			paramMap = new HashMap<>();
			paramMap.put("userId",userId);
			paramMap.put("formName",formName);
			result = functionDao.getButtonPosition(paramMap);
		}
		return result;
	}

	@Override
	public List<Function> getFunctionsNoButton(String userId) {
		List<Function> result = new ArrayList<>();

		Map<String, Object> paramMap = new HashMap<>();

		if("admin".equals(userId)){
			result = functionDao.getFunctionsNoButtonByAdmin(paramMap);
		}else{
			paramMap.put("userId",userId);
			result = functionDao.getFunctionsNoButtonByUserId(paramMap);
		}

		return result;
	}

}
