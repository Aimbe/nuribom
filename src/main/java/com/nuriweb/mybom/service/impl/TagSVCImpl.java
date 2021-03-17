package com.nuriweb.mybom.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuriweb.mybom.model.dao.inf.ITagDAO;
import com.nuriweb.mybom.model.vo.CenterVO;
import com.nuriweb.mybom.model.vo.TagVO;
import com.nuriweb.mybom.model.vo.virtual.CenterRowVO;
import com.nuriweb.mybom.service.inf.ITagSVC;

@Service
public class TagSVCImpl implements ITagSVC {

	@Autowired
	ITagDAO tgDao;
	
	
	@Override
	public List<TagVO> selectAllTagList() {
		return tgDao.selectAllTagList();
	}

	@Override
	public List<TagVO> selectAllTagListOfOneCenter(CenterVO ct) {
		return tgDao.selectAllTagListOfOneCenter(ct);
	}

	@Override
	public List<TagVO> selectAllTagListOfOneCenter(int ctId) {
		return tgDao.selectAllTagListOfOneCenter(ctId);
	}

	// 아이디당 태그리스트(문자열) 리턴
	public List<String> selectAllTagsOfOneCenter(CenterVO ct) {
		List<TagVO> tagVoList = tgDao.selectAllTagListOfOneCenter(ct);
		List<String> tagList = new ArrayList<>();
		for (TagVO tag : tagVoList) {
			tagList.add(tag.getTag());
		}
		return tagList;
	}
	
	
	public List<String> selectAllTagsOfOneCenter(CenterRowVO ct) {
		List<TagVO> tagVoList = tgDao.selectAllTagListOfOneCenter(ct);
		List<String> tagList = new ArrayList<>();
		for (TagVO tag : tagVoList) {
			tagList.add(tag.getTag());
		}
		return tagList;
	}
	
	
	public List<String> selectAllTagsOfOneCenter(int ctId) {
		return null;
	}
	
	@Override
	public int getTagCountOfCenter(int ctId) {
		return tgDao.getTagCountOfCenter(ctId);
	}

	@Override
	public int getTagCountOfCenter(CenterVO ct) {
		return tgDao.getTagCountOfCenter(ct);
	}

	@Override
	public List<TagVO> selectAllCentersByTag(String tag) {
		return tgDao.selectAllCentersByTag(tag);
	}

	@Override
	public boolean insertTag(String tag, CenterVO ct) {
		return tgDao.insertTag(tag, ct);
	}

	@Override
	public boolean insertTag(String tag, int ctId) {
		return tgDao.insertTag(tag, ctId);
	}
	
	// 태그 배열로 받아서 insert하기 
	@Override
	public boolean insertTags(String[] tags, int ctId) {
		for (String tag : tags) {
			boolean b = tgDao.insertTag(tag, ctId);
			if( !b ) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean updateTagOfCenter(String tag, int tagId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteTagOfCenter(String tag, CenterVO ct) {
		return tgDao.deleteTagOfCenter(tag, ct);
	}

	@Override
	public boolean deleteTagOfCenter(String tag, int ctId) {
		return tgDao.deleteTagOfCenter(tag, ctId);
	}

	@Override
	public boolean deleteAllTagsOfCenter(int ctId) {
		return tgDao.deleteAllTagsOfCenter(ctId);
	}

}
