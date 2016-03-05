package com.mango.vote.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mango.vote.service.IVoteCommentService;
import com.opensymphony.xwork2.ActionSupport;
@Controller("voteOptionAction")@Scope("prototype")
public class VoteOptionAction extends ActionSupport{
	@Resource(name="voteCommentService")private IVoteCommentService service=null;

	public IVoteCommentService getService() {
		return service;
	}
	public void setService(IVoteCommentService service) {
		this.service = service;
	}
}
