<%@ include file="header.jsp" %>
<body>
<div class="header">
    <div class="overlay"></div>
    <div class="text title">Job Scheduler</div>
</div>
<div class="content">
    <form class="login-form" method="post">
        <div class="right">
        </div>
        <label for="email">E-mail</label>
        <input type="text" id="email" name="email" value="Email" />
        <div class="clear"></div>
        <label for="password">Password</label>
        <input type="password" id="password" name="password" value="23456" />
        <div class="clear"></div>
        <br/>
        <input type="submit" value="Log in" />
        <div class="clear"></div>
    </form>
</div>
<%@ include file="footer.jsp" %>