package site.azhe.otoken.service.admin.service;

import site.azhe.otoken.service.admin.domain.TbSysUser;

public interface AdminService {

    /**
     * 注册
     * @param tbSysUser
     */
    public void register(TbSysUser tbSysUser);

    /**
     * 登录
     * @param loginCode 登录账号
     * @param planPassword 明文密码
     * @return
     */
    public TbSysUser login(String loginCode, String planPassword);
}
