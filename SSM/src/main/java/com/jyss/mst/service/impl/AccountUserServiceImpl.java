package com.jyss.mst.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyss.mst.entity.AccountUser;
import com.jyss.mst.mapper.AccountUserMapper;
import com.jyss.mst.service.AccountUserService;
import com.jyss.mst.utils.CommTool;
import com.jyss.mst.utils.PasswordUtil;

@Service
public class AccountUserServiceImpl implements AccountUserService {
	@Autowired
	private AccountUserMapper auMapper;

	@Override
	public AccountUser getAuBy(String username) {
		// TODO Auto-generated method stub
		return auMapper.getAuBy(username);
	}

	@Override
	public int getAuNum(String username) {
		// TODO Auto-generated method stub
		return auMapper.getAuNum(username);
	}

	@Override
	public int upHtPwd(String username, String password, String salt) {
		// TODO Auto-generated method stub
		password = PasswordUtil.generate(password, salt);
		return auMapper.upHtPwd(username, password, salt);
	}

	@Override
	public Set<AccountUser> getPermissionBy(String username) {
		// TODO Auto-generated method stub
		return auMapper.getPermissionBy(username);
	}

	@Override
	public AccountUser getAuByUsernameAndPassword(String username,
			String password) {
		// TODO Auto-generated method stub
		return auMapper.getAuByUsernameAndPassword(username, password);
	}

	@Override
	public List<AccountUser> getAuByUsername(String username) {
		// TODO Auto-generated method stub
		return auMapper.getAuByUsername(username);
	}

	@Override
	public int addAccount(AccountUser au) {
		// TODO Auto-generated method stub
		au.setName(au.getUsername());
		au.setStatus(1);
		au.setPassword("666666");
		au.setSalt(CommTool.getSalt());
		au.setPassword(PasswordUtil.generate(au.getPassword(), au.getSalt()));
		return auMapper.addAccount(au);
	}

	@Override
	public int deleteAccounts(List<Long> ids) {
		// TODO Auto-generated method stub
		return auMapper.deleteAccounts(ids);
	}

	@Override
	public int upAccountStatus(List<Long> ids, String status) {
		// TODO Auto-generated method stub
		return auMapper.upAccountStatus(ids, status);
	}

	@Override
	public int upAccount(AccountUser au) {
		// TODO Auto-generated method stub
		au.setName(au.getUsername());
		au.setStatus(1);
		au.setSalt(CommTool.getSalt());
		au.setPassword("666666");
		au.setPassword(PasswordUtil.generate(au.getPassword(), au.getSalt()));
		return auMapper.upAccount(au);
	}

	@Override
	public List<AccountUser> getRoles() {
		// TODO Auto-generated method stub
		return auMapper.getRoles();
	}

}
