<link rel="stylesheet" type="text/css" href="./codebase/style.css" " />
<%@ taglib uri="/struts-tags" prefix="S" %>

<%@ taglib uri="/struts-tags" prefix="S" %>

<div class="text title">Inscription</div>
<div class="container-register">
    <S:form action="register">
        <S:textfield name="email" label="Email"></S:textfield>
        <S:textfield name="name" label="Name"></S:textfield>
        <S:textfield name="password" label="Password"></S:textfield>
        <S:submit value="register"></S:submit>
    </S:form>
</div>