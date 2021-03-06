package com.secondhandmarket.action.goodmodule;

import com.opensymphony.xwork2.ActionSupport;
import com.secondhandmarket.service.GoodModule;

public class CheckGoodAction extends ActionSupport{
	private int gid;
	private GoodModule goodModule;
	
	@Override
	public String execute(){
		this.goodModule.checkGoodInfo(gid);
		return SUCCESS;
	}

	public int getGid() {
		return gid;
	}

	public void setGid(int gid) {
		this.gid = gid;
	}

	public GoodModule getGoodModule() {
		return goodModule;
	}

	public void setGoodModule(GoodModule goodModule) {
		this.goodModule = goodModule;
	}
}
