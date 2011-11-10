package com.redsun.platf.dao.sys;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.redsun.platf.entity.sys.SystemRole;
import com.redsun.platf.entity.sys.SystemUser;

/**
 * 角色对象的泛型DAO.
 * 
 * @author calvin
 */
@Component
public class SystemRoleDao extends HibernateDao<SystemRole, Long> {

	private static final String QUERY_SystemUser_BY_SystemRoleID = "select u from SystemUser u left join u.SystemRoleList r where r.id=?";

	/**
	 * 重载函数,因为SystemRole中没有建立与SystemUser的关联,因此需要以较低效率的方式进行删除SystemUser与SystemRole的多对多中间表.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void delete(Long id) {
		SystemRole systemRole = get(id);
		//查询出拥有该角色的用户,并删除该用户的角色.
		List<SystemUser> systemUsers = createQuery(QUERY_SystemUser_BY_SystemRoleID, systemRole.getId()).list();
		for (SystemUser u : systemUsers) {
			u.getRoleList().remove(systemRole);
		}
		super.delete(systemRole);
	}
}
