package com.secondhandmarket.action.saleradmodule;

import com.opensymphony.xwork2.ActionSupport;
import com.secondhandmarket.service.SalerAdModule;

public class CheckPostGoodListAction extends ActionSupport{

	private int posterId;
	private SalerAdModule salerAdModule;
	
	@Override
	public String execute(){
		this.salerAdModule.checkPostGoodList(posterId);
		return SUCCESS;
	}

	public int getPosterId() {
		return posterId;
	}

	public void setPosterId(int posterId) {
		this.posterId = posterId;
	}

	public SalerAdModule getSalerAdModule() {
		return salerAdModule;
	}

	public void setSalerAdModule(SalerAdModule salerAdModule) {
		this.salerAdModule = salerAdModule;
	}
}
