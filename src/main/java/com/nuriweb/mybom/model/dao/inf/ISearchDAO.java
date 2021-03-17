package com.nuriweb.mybom.model.dao.inf;

import java.util.List;

import com.nuriweb.mybom.model.vo.CenterVO;

public interface ISearchDAO {
	// 검색어로 센터리스트를 조회할 수 있다.
	List<CenterVO> selectAllCentersBySearch(String category, String concern, String orderBy);
	List<CenterVO> selectAllCentersBySearchPG(String category, String concern, String orderBy, int offset, int limit );
}
