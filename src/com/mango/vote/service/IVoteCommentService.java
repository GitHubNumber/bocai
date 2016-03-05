package com.mango.vote.service;

import java.util.List;

import com.mango.bbs.bean.TopicTitle;
import com.mango.bean.QueryResult;
import com.mango.vote.bean.VoteComment;

public interface IVoteCommentService {
	public abstract boolean addVoteOption(TopicTitle topicTitle,List<VoteComment> votelist);
	public abstract QueryResult<VoteComment> getVoteOption(int voteid);
}
