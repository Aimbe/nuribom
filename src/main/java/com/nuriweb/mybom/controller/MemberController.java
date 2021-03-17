package com.nuriweb.mybom.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.inject.Inject;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.nuriweb.mybom.Materials;
import com.nuriweb.mybom.MyCode;
import com.nuriweb.mybom.model.dao.impl.MemberMySqlDAOImpl;
import com.nuriweb.mybom.model.dao.inf.ICenterDAO;
import com.nuriweb.mybom.model.dao.inf.IReviewDAO;
import com.nuriweb.mybom.model.vo.AllNotifiVO;
import com.nuriweb.mybom.model.vo.BoardVO;
import com.nuriweb.mybom.model.vo.CenterVO;
import com.nuriweb.mybom.model.vo.LikeVO;
import com.nuriweb.mybom.model.vo.MemberVO;
import com.nuriweb.mybom.model.vo.QuestionVO;
import com.nuriweb.mybom.model.vo.ReviewVO;
import com.nuriweb.mybom.model.vo.virtual.CenterRowVO;
import com.nuriweb.mybom.service.inf.IAllNotifiSVC;
import com.nuriweb.mybom.service.inf.IBoardSVC;
import com.nuriweb.mybom.service.inf.ICenterSVC;
import com.nuriweb.mybom.service.inf.ILikeSVC;
import com.nuriweb.mybom.service.inf.IMemberSVC;
import com.nuriweb.mybom.service.inf.IQuestionSVC;
import com.nuriweb.mybom.service.inf.IReviewSVC;

@Controller
public class MemberController {

	@Autowired
	IMemberSVC mbSvc;
	
	
	@Autowired
	ILikeSVC likeSvc;
	@Autowired
	ICenterSVC ctSvc;
	@Autowired
	IBoardSVC atSvc;
	@Autowired
	IReviewSVC rvSvc;
	@Autowired
	IQuestionSVC qsSvc;
	@Autowired
	ICenterDAO ctDAO;
	@Autowired
	IReviewDAO rvDAO;
	@Autowired
	IAllNotifiSVC noSvc;
	@Inject // 서비스를 호출하기 위해서 의존성을 주입
	JavaMailSender mailSender; // 메일 서비스를 사용하기 위해 의존성을 주입함.


	// test
	@RequestMapping(value = "member_free_board.bom", method = RequestMethod.GET)
	public ModelAndView testPage() {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/member_free_board");
		return mav;
	}

	// 로그인 할 수 있다. (로그인 폼 준비)
	@RequestMapping(value = "member_login_form.bom", method = RequestMethod.GET)
	public String defaultMember() {
		return "member/mb_login_form";
	}

	// 로그인 할 수 있다.
	@RequestMapping(value = "member_login.bom", method = RequestMethod.POST)
	public ModelAndView memberLoginProc(HttpSession ses, String email, String password) { // , HttpServletRequest req) {
//		String login  = req.getParameter("login");
		// 파람 name과 동일한 인자들..
		System.out.println("memberLoginProc : login = " + email + ", pw = " + password);

		ModelAndView mav = new ModelAndView();

		int loginResult = mbSvc.loginProcess(email, password);

		if (loginResult == MyCode.MBLOGIN_AUTH) { // OK
			System.out.println(">> 로그인 성공 " + email);
			// 세션 처리
			ses.setAttribute("mbLoginName", email); // 로그인 중... UQ
			MemberVO mb = mbSvc.selectOneMemberbyEmail(email);

			
			List<AllNotifiVO> noList2 = noSvc.selectAllNotifiWithMbId(mb.getId());
			
			int noSize = noList2.size();
			ses.setAttribute("noSize",noSize);
			ses.setAttribute("noList", noList2);
			
			// 출석부... 알람...
			ses.setAttribute("mbPKId", mb.getId()); // 로그인 중... PK
//			ses.setAttribute("mbObj", mb); // 로그인 중...
			ses.setAttribute("mbnickName", mb.getNickname());
			ses.setAttribute("mbLoginTime", System.currentTimeMillis());// 로그인 시작 시간..
			
			List<CenterRowVO> ctList = ctSvc.selectAllCentersForVirtualRowLikes();

			mav.addObject("ctList", ctList);
			
			mav.addObject("msg", "로그인 성공");
			
			if(mb.getId()==1) {
				mav.setViewName("admin/admin_dashboard");
				return mav;
			}
			mav.setViewName("home");// mypage.my
			System.out.println("성공");
			System.out.println(ses.getAttribute("mbPKId"));
		} else {
			String errMsg = MyCode.LOGIN_MSGS[loginResult];
			mav.addObject("msg", "로그인 실패 : " + errMsg);
			ses.setAttribute("msg", "이메일과 비밀번호를 확인해주세요");
			mav.setViewName("member/mb_login_form");
			System.out.println("실패");
		}

		return mav;
	}

	// - 로그아웃 할 수 있다.
	// member_logout.my; get proc dao/세션, 회원
	@RequestMapping(value = "member_logout.bom", method = RequestMethod.GET)
	public String memberLogoutProc(HttpSession ses) {
		String login = (String) ses.getAttribute("mbLoginName");

		ses.invalidate(); // 세션 무효화..
		return "redirect:/"; // /mysns 이동.. 문맥경로

	}

	// 가입한 이메일로 비밀번호를 전달받을수있다 (비밀번호찾기)
//		member_search_pw_form.bom 	 // get form 비회원
	@RequestMapping(value = "member_search_pw_form.bom", method = RequestMethod.GET)
	public ModelAndView memberSearchPwForm(HttpSession ses) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/mb_search_pw");
		return mav;
	}
	
	// 비번 질문/답변 일치 확인 
	@RequestMapping(value = "pwQaCheck.bom", method = RequestMethod.GET)
	@ResponseBody
	public String confirmPwQa(@RequestParam(value = "email") String email,
			@RequestParam(value = "pwQuestion") int pwQuestion, 
			@RequestParam(value = "pwAnswer") String pwAnswer,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		// 클라이언트에 비동기 통신으로 받아줘야하니까 @ReqeustBody를 사용한다.

		// 값확인
//				System.out.println("중복 확인 요청된 닉네임 : " + nickname);
		MemberVO mb = mbSvc.selectOneMemberbyEmail(email);
		String pwAnwerDB = mb.getPwAnswer();
		int pwQuestionDB = mb.getPwQuestion();

		String result = "";
		if (mb != null ) {
			
			if (pwAnswer.equals(pwAnwerDB) && 
					pwQuestion == pwQuestionDB) { // 일치
				result = "success";
			} else {
				result = "fail";
			}
		}
		return result;

	}
	
// 비번 메일 전송
	@RequestMapping(value = "/password_send_email.bom", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView pwSendEmail(HttpSession ses, @RequestParam(value = "email") String email,
			HttpServletResponse response_email) throws IOException {
		
		MemberVO mb = mbSvc.selectOneMemberbyEmail(email);
		String pwDB = mbSvc.selectPwByEmail(email);
		String setfrom = "nuribom2021@gamil.com";
		String tomail = email; // 받는 사람 이메일
		String title = "누리봄 비밀번호 입니다."; // 제목
		String content =

				System.getProperty("line.separator") + // 한줄씩 줄간격을 두기위해 작성
						System.getProperty("line.separator") +
						"안녕하세요 " + mb.getNickname()+"회원님 누리봄을 찾아주셔서 감사합니다"
						+ System.getProperty("line.separator") +
						System.getProperty("line.separator") +
						" 회원님의 비밀번호는 " + pwDB + " 입니다. "
						+ System.getProperty("line.separator") +
						System.getProperty("line.separator") +
						"받으신 비밀번호로 로그인해주세요 "
						+ "감사합니다."; // 내용
		ModelAndView mav = new ModelAndView();
		String result = "";
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

			messageHelper.setFrom(setfrom); // 보내는사람 생략하면 정상작동을 안함
			messageHelper.setTo(tomail); // 받는사람 이메일
			messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
			messageHelper.setText(content); // 메일 내용

			mailSender.send(message);

			result = "success";
			System.out.println(result);
			mav.setViewName("member/mb_search_pw_success");
			return mav;
		} catch (Exception e) {
			System.out.println(e);
			mav.setViewName("member/mb_seach_pw");
		}
		response_email.setContentType("text/html; charset=UTF-8");
		return mav;
	}
	

//				member_search_pw_send.bom		 // get proc 회원
//				(이메일 전송 성공 시)member_search_pw_success.bom // get form
		@RequestMapping(value = "member_search_pw_success.bom", method = RequestMethod.GET)
		public ModelAndView searchPwSuccess() {
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName("member/mb_search_pw_success");

			return mav;
		}
//		- 가입 할 수  있다. (패스워드 암호화 저장)
//		member_new_form.my; get form 비회원
	@RequestMapping(value = "member_new_form.bom", method = RequestMethod.GET)
	public ModelAndView memberNewForm() {
		System.out.println("MemberNewFormController");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/mb_new_form");

		return mav;
	}

	// member_join.my; post proc dao 비회원
	@RequestMapping(value = "member_join.bom", method = RequestMethod.POST)
	public ModelAndView memberJoinProc(HttpServletRequest request,HttpSession ses) {
		System.out.println("MemberJoinController");
		// 요청파람, 세션 처리
		String email = request.getParameter("email");
		String nickname = request.getParameter("nickname");
		String password = request.getParameter("password");

		String[] pwQuestions = request.getParameterValues("pwQuestion");
		int pwQuestion = Integer.parseInt(pwQuestions[0]);
		String pwAnswer = request.getParameter("pwAnswer");

		ModelAndView mav = new ModelAndView();

		// 부가기능 DAO에서 체크 - 로그인 중복체크
		if (email != null && !email.isEmpty()) {
			boolean dup = mbSvc.isDuplicatedNick(nickname);
			if (dup) { // 이미 사용중.. 선점..
				mav.addObject("msg", email + "은 이미 사용 된 email 입니다. ");
				mav.setViewName("member/mb_new_form"); // fw
				return mav;
			}
		} else {
			mav.addObject("msg", "회원 가입 실패... login명 파람 error");
			mav.setViewName("member/mb_new_form"); // fw
			return mav;
		}

		MemberVO mb = new MemberVO(email, nickname, password, pwQuestion, pwAnswer);
		boolean b = this.mbSvc.insertNewMemberWithCrypto(mb); // AES 암호화
		// view 패스 분기처리 (forward, redirect) => mav 리턴
		if (b) {
			mav.addObject("msg", "회원 가입 성공");
			mav.setViewName("member/mb_join_success");
			// 리다이렉션 url 재지정
			
			boolean c = noSvc.insertNewMemberNotifi(mbSvc.selectOneMemberbyEmail(email).getId(), "관리자", MyCode.NewMemberNotifi);
			
			if( c) {
				System.out.println("회원가입 알림 쏴주기 완료");
			}else {
				
				System.out.println("회원가입 알림 쏴주기 실패.");
			}
			
		} else {
			mav.addObject("msg", "회원 가입 실패... DB");
			mav.setViewName("member/mb_new_form"); // fw
		}
		return mav;
	}

	// 이메일 중복확인 체크 요청
	@RequestMapping(value = "emailCheck.bom", method = RequestMethod.GET)
	@ResponseBody
	public String confirmEmail(String email) throws Exception {
		// 클라이언트에 비동기 통신으로 받아줘야하니까 @ReqeustBody를 사용한다.

		// 값확인
//			System.out.println("중복 확인 요청된 이메일 : " + email);

		Map<String, Object> data = new HashMap<>();

		// 서비스 측에 요청
		boolean dup = mbSvc.isDuplicatedEmail(email);
		String result = "";
		if (dup) { // 이미 사용중.. 선점..
			result = "fail";
		} else {

			result = "success";
		}

		return result;
	}

	// 메일 인증
	// mailSending 코드
	@RequestMapping(value = "/member_mail_send.bom", method = RequestMethod.GET)
	@ResponseBody
	public String mailSending(HttpSession ses, @RequestParam(value = "email") String email,
			HttpServletResponse response_email) throws IOException {

		Random r = new Random();
		int dice = r.nextInt(4589362) + 49311; // 이메일로 받는 인증코드 부분 (난수)
		ses.setAttribute("key", dice);
		String setfrom = "nuribom2021@gamil.com";
		String tomail = email; // 받는 사람 이메일
		String title = "누리봄 회원가입 인증 이메일 입니다."; // 제목
		String content =

				System.getProperty("line.separator") + // 한줄씩 줄간격을 두기위해 작성
						System.getProperty("line.separator") +
						"안녕하세요 회원님 누리봄을 찾아주셔서 감사합니다"
						+ System.getProperty("line.separator") +
						System.getProperty("line.separator") +
						" 인증번호는 " + dice + " 입니다. "
						+ System.getProperty("line.separator") +
						System.getProperty("line.separator") +
						"받으신 인증번호를 홈페이지에 입력해 주시면 인증이 완료됩니다. "
						+ "감사합니다."; // 내용

		String result = "";
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

			messageHelper.setFrom(setfrom); // 보내는사람 생략하면 정상작동을 안함
			messageHelper.setTo(tomail); // 받는사람 이메일
			messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
			messageHelper.setText(content); // 메일 내용

			mailSender.send(message);

			result = "success";
			System.out.println(result);
		} catch (Exception e) {
			System.out.println(e);
			result = "fail";
		}
		response_email.setContentType("text/html; charset=UTF-8");
		return result;
	}

	// 인증번호 확인
	@RequestMapping(value = "member_code_check.bom", method = RequestMethod.GET)
	@ResponseBody
	public String confirmCode(int code, HttpSession ses, HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		System.out.println("확인 요청된 code : " + code);
		int key = (int) ses.getAttribute("key");
		System.out.println("확인 요청된 key : " + key);
		String result = "";
		if (key == code) {
			result = "success";
		} else {
			result = "fail";
		}
		return result;
	};

	// 닉네임 중복확인 체크 요청

	@RequestMapping(value = "nickCheck.bom", method = RequestMethod.GET)
	@ResponseBody
	public String confirmId(String nickname, HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		// 클라이언트에 비동기 통신으로 받아줘야하니까 @ReqeustBody를 사용한다.

		// 값확인
//			System.out.println("중복 확인 요청된 닉네임 : " + nickname);

		String id = request.getParameter("nickname");
		String result = "";
		if (id != null && !id.isEmpty()) {
			boolean dup = mbSvc.isDuplicatedNick(id);
			if (dup) { // 이미 사용중.. 선점..
				result = "fail";
			} else {
				result = "success";
			}
		}
		return result;

	}

	// 마이페이지에서 내가 쓴 리뷰를 확인 할 수 있다.
	@RequestMapping(value = "member_review.bom", method = RequestMethod.GET)
	public ModelAndView myReviewListProc(HttpSession ses,
			@RequestParam(value = "pg", required = false, defaultValue = "1") int page) {

		if ((Integer) ses.getAttribute("mbPKId") == null) {
			return new ModelAndView("member/mb_login_form");
		} else {
			int mbId = (Integer) ses.getAttribute("mbPKId");
			int maxPg = rvSvc.checkMaxPageNumberMyReview(mbId);
			if (maxPg == 0) {
				maxPg = 1;
			}
			if (page <= 0 || page > maxPg) {
				System.out.println(">> 잘못된 페이지번호 요청 pg :" + page);
				return new ModelAndView("redirect:member_review.bom?pg=1");
			}
			List<ReviewVO> atList = rvSvc.getListByMember(mbId, page);
			System.out.println(atList.size());
			ModelAndView mav = new ModelAndView("member/mb_review");
			mav.addObject("subCategory", Materials.SUB_CATEGORY_MY_REVIEW);
			if (atList != null) {
				int atSize = atList.size(); // 1~5
				mav.addObject("msg", "pg 게시글 리스트 조회 성공: " + atSize + "개");
				mav.addObject("atList", atList);
				mav.addObject("atSize", atSize); // 0개 이상
				mav.addObject("mbId", mbId);
				for (ReviewVO rvv : atList) {
					CenterVO ct = ctDAO.getOneCenter(rvv.getReviewCenterId());
					rvv.setReviewCenterName(ct.getName());
				}
				// ${pn}, ${param.pg}
				mav.addObject("pn", page); // 요청으로 활성화된 페이지 번호
//				int maxPg = atSvc.checkMaxPageNumber();
				System.out.println("ctrl: maxPg = " + maxPg);
				mav.addObject("maxPg", maxPg); // 최대 페이지 번호
			}
			return mav;
		}
	}

	// 마이페이지에서 리뷰를 삭제 할 수 있다.
	@RequestMapping(value = "delete_my_review.bom", method = RequestMethod.GET)
	public ModelAndView reviewDeleteMypage(HttpSession ses, @RequestParam(value = "bdId") List<Integer> bdList,
			int mbId) {

		ModelAndView mav = new ModelAndView();
		mav.addObject("subCategory", Materials.SUB_CATEGORY_MY_REVIEW);
		// mav.addObject("subCategory",Materials.SUB_CATEGORY_FREE_BOARD);
		// 마이페이지용으로 처리

		int mbPKId = (int) ses.getAttribute("mbPKId");

		if (bdList != null) { // 체크박스 잘 들어왔나 확인.

			for (Integer bd : bdList) {
				System.out.println(">> 넘어온 체크리스트 값: " + bd);
			}
		}

		if (bdList != null && mbId != 0) {
			List<ReviewVO> rvList = new ArrayList<>();
			for (Integer bd : bdList) {
				ReviewVO rv = rvDAO.selectOneReview(bd);
				rvList.add(rv);
			}
			if (mbPKId == mbId || mbPKId == 1) {

				boolean b = rvSvc.deleteListReview(rvList);

				if (b) {

					System.out.println(">> 해당 게시글 삭제 성공:" + rvList.size() + "개");
					mav.setViewName("redirect:member_review.bom");

				} else {

					System.out.println(">> 해당 게시글 삭제 실패:" + rvList.size() + "개");
					mav.setViewName("redirect:member_review.bom");
					mav.addObject("deleteFailMsg", "게시글 삭제에 실패하셨습니다.");
				}

			}
		} else {
			// null..
			System.out.println(">> 파람 오류! bdList, mbId 확인 요망");
			mav.addObject("deleteFailMsg", "게시글 삭제에 실패하셨습니다. 파람에러");
			mav.setViewName("redirect:member_free.bom");
		}

		return mav;
	}

	// 마이페이지에서 내가 쓴 자유글을 확인 할 수 있다.
	@RequestMapping(value = "member_free.bom", method = RequestMethod.GET)
	public ModelAndView myFreeList(HttpSession ses,
			@RequestParam(value = "pg", required = false, defaultValue = "1") int page) {
		if ((Integer) ses.getAttribute("mbPKId") == null) {
			return new ModelAndView("member/mb_login_form");
		} else {
			int mbId = (Integer) ses.getAttribute("mbPKId");
			int maxPg = atSvc.checkMaxPageNumberMyBoard(atSvc.FREE_BOARD, atSvc.PAGE_SIZE, mbId);
			if (maxPg == 0) {
				maxPg = 1;
			}
			if (page <= 0 || page > maxPg) {
				System.out.println(">> 잘못된 페이지번호 요청 pg :" + page);
				return new ModelAndView("redirect:member_free.bom?pg=1");
			}
			List<BoardVO> atList = atSvc.selectAllBoardsByUser(atSvc.FREE_BOARD, page, atSvc.PAGE_SIZE, mbId);
			System.out.println(atList.size());
			ModelAndView mav = new ModelAndView("member/mb_free");
			mav.addObject("subCategory", Materials.SUB_CATEGORY_MY_FREE);
			if (atList != null) {
				int atSize = atList.size(); // 1~5
				mav.addObject("msg", "pg 게시글 리스트 조회 성공: " + atSize + "개");
				mav.addObject("atList", atList);
				mav.addObject("atSize", atSize); // 0개 이상
				mav.addObject("mbId", mbId); // 0개 이상
				// ${pn}, ${param.pg}
				mav.addObject("pn", page); // 요청으로 활성화된 페이지 번호
//								int maxPg = atSvc.checkMaxPageNumber();
				System.out.println("ctrl: maxPg = " + maxPg);
				System.out.println("pn" + page);
				mav.addObject("maxPg", maxPg); // 최대 페이지 번호
			} else {
				mav.addObject("msg", "pg 게시글 리스트 조회 실패"); // null
				// mav.setViewName("redirect:member_list.my"); // mypage..
			}
			return mav;
		}
	}

	// 마이페이지에서 내가 쓴 QnA을 확인 할 수 있다.
	@RequestMapping(value = "member_qna.bom", method = RequestMethod.GET)
	public ModelAndView myQnAList(HttpSession ses,
			@RequestParam(value = "pg", required = false, defaultValue = "1") int page) {
		if ((Integer) ses.getAttribute("mbPKId") == null) {
			return new ModelAndView("member/mb_login_form");
		} else {
			int mbId = (Integer) ses.getAttribute("mbPKId");
//				int maxPg = atSvc.checkMaxPageNumberMyBoard(atSvc.FREE_BOARD, atSvc.PAGE_SIZE, mbId);
			int maxPg = qsSvc.checkMaxpageNumberByUser(page, mbId);
			if (maxPg == 0) {
				maxPg = 1;
			}
			if (page <= 0 || page > maxPg) {
				System.out.println(">> 잘못된 페이지번호 요청 pg :" + page);
				return new ModelAndView("redirect:member_paq.bom?pg=1");
			}
			System.out.println("page : " + page);
			List<QuestionVO> qnaList = qsSvc.selectAllQuestionByUser(mbId, page, qsSvc.PAGE_SIZE);
			ModelAndView mav = new ModelAndView("member/mb_qna");
			mav.addObject("subCategory", Materials.SUB_CATEGORY_MY_QNA);
			if (qnaList != null) {
				int qnaSize = qnaList.size(); // 1~5
				System.out.println("qnaSize" + qnaSize);
				mav.addObject("msg", "pg 게시글 리스트 조회 성공: " + qnaSize + "개");
				mav.addObject("qnaList", qnaList);
				mav.addObject("qnaSize", qnaSize); // 0개 이상
				mav.addObject("mbId", mbId); // 0개 이상
				// ${pn}, ${param.pg}
				mav.addObject("pn", page); // 요청으로 활성화된 페이지 번호
//					int maxPg = atSvc.checkMaxPageNumber();
				System.out.println("ctrl: maxPg = " + maxPg);
				System.out.println("pn" + page);
				mav.addObject("maxPg", maxPg); // 최대 페이지 번호
			}
			return mav;
		}
	}

	// 마이페이지에서 나의 관심상담소를 확인 할 수 있다.
	@RequestMapping(value = "member_like.bom", method = RequestMethod.GET)
	public ModelAndView myLikeList(HttpSession ses,
			@RequestParam(value = "pg", required = false, defaultValue = "1") int page) {
		if ((Integer) ses.getAttribute("mbPKId") == null) {
			return new ModelAndView("member/mb_login_form");
		} else {
			int mbId = (Integer) ses.getAttribute("mbPKId");
			int maxPg = likeSvc.checkMaxpageNumberByUser(page, mbId);
			if (maxPg == 0) {
				maxPg = 1;
			}
			if (page <= 0 || page > maxPg) {
				System.out.println(">> 잘못된 페이지번호 요청 pg :" + page);
				return new ModelAndView("redirect:member_like.bom?pg=1");
			}

			List<LikeVO> likeList = likeSvc.selectAllLikeListOfOneMember(mbId, page, 3);
			System.out.println("likeList.size() : " + likeList.size());
			List<CenterVO> likeCtList = new ArrayList<>();
			for (LikeVO likeVO : likeList) {
				int ctId = likeVO.getCenterId();
				CenterVO ct = ctSvc.getOneCenter(ctId);
				likeCtList.add(ct);
			}
			System.out.println("likeCtList.size() : " + likeCtList.size());
			ModelAndView mav = new ModelAndView("member/mb_like");
			mav.addObject("subCategory", Materials.SUB_CATEGORY_MY_LIKE);
			if (likeList != null) {
				int likeSize = likeList.size(); // 1~5
				System.out.println("likeCtList" + likeCtList);
				mav.addObject("msg", "pg 좋아요 리스트 조회 성공: " + likeList + "개");
				mav.addObject("likeList", likeList);
				mav.addObject("likeCtList", likeCtList);
				mav.addObject("likeSize", likeSize); // 0개 이상
				mav.addObject("mbId", mbId); // 0개 이상
				// ${pn}, ${param.pg}
				mav.addObject("pn", page); // 요청으로 활성화된 페이지 번호
//					int maxPg = atSvc.checkMaxPageNumber();
				mav.addObject("maxPg", maxPg); // 최대 페이지 번호
			}
			return mav;
		}
	}

	// 마이페이지에서 나의 정보를 수정 할 수 있다. (폼준비)
	@RequestMapping(value = "member_info_edit.bom", method = RequestMethod.GET)
	public ModelAndView myInfoEdit(HttpSession ses) {
		if ((Integer) ses.getAttribute("mbPKId") == null) {
			return new ModelAndView("member/mb_login_form");
		} else {
			int mbId = (Integer) ses.getAttribute("mbPKId");
			MemberVO mb = mbSvc.selectOneMember(mbId);
			ModelAndView mav = new ModelAndView();
			mav.addObject("subCategory", Materials.SUB_CATEGORY_MY_INFO);
			
			if (mb != null) {

				mav.setViewName("member/mb_info_edit");
				mav.addObject("member", mb);
				mav.addObject("msg", "회원 편집 준비 성공!!");
			} else {

//					mav.setViewName("redirect:admin_member.bom");
				// mav.setViewName("redirect:member_show.my?mbId="+id);
				mav.addObject("msg", "회원 편집 준비 실패 !! + " + mb);
			}

			return mav;
		}
	}
	// 정보수정 proc

	@RequestMapping(value = "member_info_edit_proc.bom", 
			method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public String myInfoEditProc(HttpSession ses, HttpServletRequest request, 
			@RequestBody MemberVO mb) {

		int id = mb.getId();
		String email = mb.getEmail();
		String nickname = mb.getNickname();
		String password = mb.getPassword();
		int pwQuestion = mb.getPwQuestion();
		String pwAnswer = mb.getPwAnswer();

		MemberVO member = new MemberVO(id, email, nickname, password, null, null, pwQuestion, pwAnswer);

		String result = "";

		boolean b = mbSvc.updateOneMember(member);
		System.out.println(b);
		if (b) {
			result = "success";
			
			
			boolean c = noSvc.insertNewPrivateNotifi(id, nickname, MyCode.NewPrivateNotifi);
			List<AllNotifiVO> noList2 = noSvc.selectAllNotifiWithMbId(mb.getId());
			ses.setAttribute("noList", noList2);
			
			if( c) {
				System.out.println("회원수정 알림 쏴주기 완료");
			}else {
				
				System.out.println("회원수정 알림 쏴주기 실패.");
			}
			
		} else {
			result = "fail";
		}
		return result;
	}

	// 비번 수정 전 현재 비밀번호를 확인 할 수 있다.
	// member_pw_check.bom // proc dao
	@RequestMapping(value = "nowPwCheck.bom", method = RequestMethod.POST)
	@ResponseBody
	public String checkPw(@RequestBody String password, HttpSession ses) throws ServletException {

		// 값확인
		System.out.println("확인 요청된 password : " + password);
		password = password.replaceAll("\\\"", "");
		int mbId = (Integer) ses.getAttribute("mbPKId");
		MemberVO mb = new MemberVO();
		mb = mbSvc.selectOneMember(mbId);
		int loginResult = mbSvc.loginProcess(mb.getEmail(), password);
		String result = "";

		if (mbId == mb.getId()) {

			if (loginResult == MyCode.MBLOGIN_AUTH) {

				result = "success";
			} else {
				result = "fail";
			}
		} else {
			result = "fail";
		}
		return result;

	}

	// 비밀번호를 바꿀 수 있다.
	// member_update_pw.bom // get proc dao 비번변경
	@RequestMapping(value = "member_update_pw.bom", method = RequestMethod.POST)
	@ResponseBody
	public String changePw(@RequestBody String password, HttpSession ses) throws ServletException {

		// 값확인
		System.out.println("변경할 password : " + password);
		password = password.replaceAll("\\\"", "");
		int mbId = (Integer) ses.getAttribute("mbPKId");
		MemberVO mb = new MemberVO();
		mb = mbSvc.selectOneMember(mbId);

		boolean updateResult = mbSvc.updatePw(mb.getEmail(), password);
		String result = "";

		if (mbId == mb.getId()) {

			if (updateResult) {

				result = "success";
				

				boolean c = noSvc.insertNewPrivateNotifi(mbId, "관리자", MyCode.NewPrivateNotifi);
				List<AllNotifiVO> noList2 = noSvc.selectAllNotifiWithMbId(mbId);
				ses.setAttribute("noList", noList2);
				
				if( c) {
					System.out.println("회원수정 알림 쏴주기 완료");
				}else {
					
					System.out.println("회원수정 알림 쏴주기 실패.");
				}
			} else {
				result = "fail";
			}
		} else {
			result = "fail";
		}
		return result;

	}
	
//	탈퇴할 수 있다. (정보수정페이지에서)
//	member_out.bom // get proc 회원세션, 메인페이지로 이동
	@RequestMapping(value = "member_out.bom", method = RequestMethod.POST)
	@ResponseBody
	public String memberOut(@RequestBody int id, HttpSession ses) throws ServletException {
		int mbId = (Integer) ses.getAttribute("mbPKId");
		MemberVO mb = new MemberVO();
		mb = mbSvc.selectOneMember(id);
		String result = "";
		if (mbId == mb.getId()) {
			
			if (mbSvc.deleteOneMember(mbId)) {
				ses.invalidate();
				result = "success";
			} else {
				result = "fail";
			}
		} else {
			result = "fail";
		}
		return result;
	}

		


}
