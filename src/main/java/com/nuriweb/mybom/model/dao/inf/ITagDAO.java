package com.nuriweb.mybom.model.dao.inf;

import java.util.List;

import com.nuriweb.mybom.model.vo.CenterVO;
import com.nuriweb.mybom.model.vo.TagVO;
import com.nuriweb.mybom.model.vo.virtual.CenterRowVO;

public interface ITagDAO {
	
// 모든 태그리스트를 모두 조회할 수 있다.
	List<TagVO> selectAllTagList();
	
//	한개의 상담소가 지닌 태그들을 조회할 수 있다. 
	List<TagVO> selectAllTagListOfOneCenter(CenterVO ct);
	List<TagVO> selectAllTagListOfOneCenter(int ctId);
	
//	해당 상담소가 가지고 있는 태그 갯수를 조회할 수 있다.
	int getTagCountOfCenter(int ctId);
	int getTagCountOfCenter(CenterVO ct);
	
// 	해당 태그를 가지고 있는 태그리스트를 조회할 수 있다. => svcImpl에서 centerList조회 가능하도록..
	List<TagVO> selectAllCentersByTag(String tag); // + svc
	
//	상담소에 태그를 등록할 수 있다.
	boolean insertTag(String tag, CenterVO ct);
	boolean insertTag(String tag, int ctId);
	
//	상담소의 태그를 업데이트(수정)할 수 있다.
	boolean updateTagOfCenter(String tag, int tagId); //태그 아이디를 get해서 업데이트해야함
	
//	상담소의 태그를 삭제할 수 있다.
	boolean deleteTagOfCenter(String tag, CenterVO ct);
	boolean deleteTagOfCenter(String tag, int ctId);

	List<TagVO> selectAllTagListOfOneCenter(CenterRowVO ct);

	// 모든 태그 삭제
	boolean deleteAllTagsOfCenter(int ctId);
	

}
