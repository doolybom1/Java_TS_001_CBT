package com.biz.cbt.dao;

import java.util.List;

import com.biz.cbt.persistence.CbtDTO;

public interface CbtDao {
	
	public List<CbtDTO> selectAll();
	public String getMaxSeq();
	public CbtDTO findBySeq(int seq);
	public int insert(CbtDTO cbtDTO);
	public int update(CbtDTO cbtDTO);
	public int delete(int cb_seq);
}
