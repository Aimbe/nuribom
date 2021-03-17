package com.nuriweb.mybom;

import static org.junit.Assert.*;

import org.junit.Test;

import com.nuriweb.mybom.model.vo.BoardVO;

public class BoardVOTest {

	@Test
	public void testGetMbId() {
	//성공 
	BoardVO bd = new BoardVO(2,"호두","아침 메뉴", "뭐 먹을까요?", "123456");
	assertSame(2, bd.getMbId());
	
	}

	@Test
	public void testGetTitle() {
	//실패
	BoardVO bd = new BoardVO(2,"호두","아침 메뉴", "뭐 먹을까요?", "123456");
	assertEquals("저녁 메뉴",bd.getTitle());
		
	}

}
