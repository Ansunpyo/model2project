<%@ page import="vo.Member" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="./js/jquery-3.5.1.js"></script>
<script src="./js/login.js"></script>
<link rel="stylesheet" href="./css/login.css">
</head>
<body>
	<div class="loginCont">
		<div class="tabs">
			<div class="joinTab">
				Join
			</div>
			<div class="loginTab on">
				Login
			</div>
		</div>
		<div class="loginFormDiv">
			<div class="loginFormDiv2">
				<form action="#" method="post">
					<div>
						<input type="text" class="inputSlot" name="id" placeholder="ID" required="required" />
					</div>
					<div>
						<input type="password" class="inputSlot" name="pw" placeholder="Password" required="required" />
					</div>
					<div class="loginExtra">
						<label><input type="checkbox" name="RememberID" /> Remember Me</label>
						<span class="forgotPW"><a href="#"><h6>Forgot Password?</h6></a></span>
					</div>
					<div>
						<input type="submit" value="로그인" />
					</div>
				</form>
			</div>
		</div>
		<div class="joinFormDiv">
			<div class="joinFormDiv2">
				<form action="#" method="post">
					<div class="tabs2">
						<div class="studentTab on" vlaue="학생">학생회원</div>
						<div class="teacherTab" value="교사">교사회원</div>
					</div>
					<div>
						<input type="text" class="inputSlot" name="id" placeholder="ID" required="required" />
						<input type="hidden" name="classfy"/>
					</div>
					<div>
						<input type="password" class="inputSlot" name="pw" placeholder="Password" required="required" />
					</div>
					<hr/>
					<div>
						<input type="text" class="inputSlot" name="name" placeholder="Name" required="required" />
					</div>
					<div>
						<input type="text" class="inputSlot" name="email" placeholder="Email" required="required" />
					</div>
					<div class="genderDiv">
						Gender : <select name="Gender">
									<option value="남">Male</option>
									<option value="여">Female</option>
								 </select>
					</div>
					<div class="teacherInfo">
						<hr />
						<div>
							<input type="text" class="inputSlot" name="major" placeholder="Major" required="required" />
						</div>
						<div>
							<input type="text" class="inputSlot" name="education" placeholder="Education" required="required" />
						</div>
					</div>
					<div>
						<input type="submit" value="회원가입" />
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>