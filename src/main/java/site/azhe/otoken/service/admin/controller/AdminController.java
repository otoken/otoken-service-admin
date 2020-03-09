package site.azhe.otoken.service.admin.controller;

import com.ctc.wstx.util.StringUtil;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import site.azhe.otoken.common.dto.BaseResult;
import site.azhe.otoken.service.admin.domain.TbSysUser;
import site.azhe.otoken.service.admin.service.AdminService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public BaseResult login(String loginCode, String password){
        // 检查登录信息
        BaseResult baseResult = checkLogin(loginCode, password);
        if (baseResult != null){
            return baseResult;
        }

        TbSysUser tbSysUser = adminService.login(loginCode, password);
        if (tbSysUser!=null){
            return BaseResult.ok(tbSysUser);
        }
        else {
            return BaseResult.not_ok(Lists.newArrayList(
                    new BaseResult.Error("", "登录失败")
            ));
        }
    }

    /**
     * 检查登录
     * @param loginCode
     * @param password
     * @return
     */
    private BaseResult checkLogin(String loginCode, String password){
        BaseResult baseResult = null;
        List<BaseResult.Error> errors = Lists.newArrayList();

        if (StringUtils.isBlank(loginCode) || StringUtils.isBlank(password)){
            baseResult = BaseResult.not_ok(Lists.newArrayList(
                    new BaseResult.Error("loginCode", "登录账户不能为空"),
                    new BaseResult.Error("password", "密码不能为空")
            ));
        }
        return baseResult;
    }
}
