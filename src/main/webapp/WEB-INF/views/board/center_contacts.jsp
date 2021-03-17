<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/_common.jsp" %>
<%@ include file="../common/_board_layout.jsp" %>
    
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>누리봄</title>
  
<link href=" ${pageContext.request.contextPath}/resources/css/center_contacts.css" rel="stylesheet" type="text/css" />
   
  </head>
  <body>
       <%@include file="../common/_header.jsp" %>  

    <div id="main">
    <%@include file="../common/board/_visual.jsp" %>
      
<!-- 카테고리 레이아웃 -->
	 <div class="container submain-content-box">
 	 <%@include file="../common/board/_nuri_board_category.jsp" %>

          <!--여기에 내용..-->
          <div class="real-content">
            <!--이안에 코딩해주세요~~-->

            <div class="contact_wrapper">
              <div class="contact_tit">
                상담 <i class="fas fa-circle"></i> 신고
              </div>
              <div class="contact_box">
                <a href="http://www.mogef.go.kr/msv/metooReport.do">
                  <div class="real_contact">
                    <i
                      class="fas fa-bell fa-2x"
                      style="color: rgb(85, 35, 35)"
                    ></i
                    ><br />
                    성희롱 성폭력 <br />신고센터
                    <br />
                    <span id="num1">02-735-7544</span>
                  </div>
                </a>
                <a href="https://d4u.stop.or.kr/">
                  <div class="real_contact">
                    <i
                      class="fas fa-exclamation-triangle fa-2x"
                      style="color: rgb(155, 96, 24)"
                    ></i
                    ><br />
                    디지털 성범죄 <br />피해자 지원센터 <br />
                    <span id="num2"> 02-735-8994</span>
                  </div>
                </a>
                <a href="https://www.women1366.kr/_main/main.html">
                  <div class="real_contact no-icon">
                    여성긴급전화 <br />
                    <span id="num3">1366</span>
                  </div>
                </a>

                <a href="http://www.mogef.go.kr/cc/tlc/cc_tlc_f001.do">
                  <div class="real_contact no-icon">
                    여성일자리 상담 <br />
                    <span id="num4">1544-1199</span>
                  </div>
                </a>

                <a
                  href="https://www.cyber1388.kr:447/new_/helpcall/helpcall_n_01.asp?id=top_simter"
                >
                  <div class="real_contact no-icon">
                    청소년전화<br />
                    <span id="num5"
                      ><span id="col1">1</span><span id="col2">3</span
                      ><span id="col3">8</span><span id="col4">8</span>
                    </span>
                  </div>
                </a>

                <a href="http://www.mogef.go.kr/cc/opc/cc_opc_f002.do">
                  <div class="real_contact no-icon">
                    가족상담전화<br />
                    <span id="num6">1644-6621</span>
                  </div>
                </a>

                <a href="http://www.spckorea.or.kr/">
                  <div class="real_contact no-icon">
                    자살예방상담전화<br />
                    <i class="fas fa-heartbeat fa-2x" style="color: pink"></i>
                    <span id="num7">1393</span>
                  </div>
                </a>

                <a
                  href="https://www.liveinkorea.kr/center/page/contents.do?menuSeq=180"
                >
                  <div class="real_contact no-icon">
                    다누리콜센터<br />(다문화가족 지원)<br />
                    <span id="num8">1577-1366</span>
                  </div>
                </a>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!--content 영역-->
    </div>
    <!--main-->

    <div id="footer">
      <div class="container footer-container">
        <h1 class="logo footer-logo">누리봄</h1>
        <div class="footer-category">
          <ul>
            <li><a href="#">누리봄스토리</a></li>
            <li><a href="#">자주 묻는 질문</a></li>
            <li><a href="#">이용약관</a></li>
            <li><a href="#">개인정보처리방침</a></li>
          </ul>

          <div class="footer-sns">
            <a href="#"><i class="fab fa-twitter fa-3x"></i></a>
            <a href="#"><i class="fab fa-instagram fa-3x"></i></a>
            <a href="#"><i class="fab fa-facebook-f fa-3x"></i></a>
          </div>
        </div>

        <span class="footer-line"></span>

        <span class="footer-info first-info">제휴문의 nuribom@nuribom.com</span>
        <span class="footer-info"
          >서울시 성동구 왕십리로 303 4층 TEL: 02-428-9694</span
        >
        <span class="footer-info">&copy;2020 nuribom</span>
      </div>
    </div>

    <div id="side-fix-btn">
      <div class="side-inner-btn">
        <a href="#">
          <i class="fas fa-home fa-2x home-btn"></i>
        </a>
      </div>
      <div class="side-inner-btn">
        <a href="#">
          <i class="fas fa-search fa-2x search-btn"></i>
        </a>
      </div>
      <div class="side-inner-btn">
        <a href="#">
          <i class="fas fa-heart fa-2x like-btn"></i>
        </a>
      </div>
      <div class="side-inner-btn">
        <a href="#">
          <i class="fas fa-angle-up fa-2x scroll-up-btn"></i>
        </a>
      </div>
    </div>
  </body>
</html>
