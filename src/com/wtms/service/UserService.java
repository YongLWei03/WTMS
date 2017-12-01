package com.wtms.service;

import com.jfinal.plugin.activerecord.Page;
import com.wtms.common.model.User;

public class UserService {
	
	private static final User dao = new User().dao();
	
	public Page<User> paginate(int pageNumber, int pageSize) {
		return dao.paginate(pageNumber, pageSize, "select *", "from wf_user order by id asc");
	}
	
	public User findById(int id) {
		return dao.findById(id);
	}
	
	public void deleteById(int id) {
		dao.deleteById(id);
	}
	
	public void addUser() {
		new User().setName("guoce").setPositionId("1").save();
	}

	public static User getByUsername(String username) {
		return User.dao.find("select * from wf_user where username='"+username+"'").get(0);
	}

}
