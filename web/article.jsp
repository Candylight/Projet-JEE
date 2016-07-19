<%@ include file="header.jsp" %>
<body>
<div class="header title">
    <div class="center">
        <div class="text">
            Votre planning
        </div>
        <div class="user-info">
            <div class="logout">
                [
                <a href="logout.action">
                    Deconnexion
                </a>
                ]
            </div>
            <div class="username">
                Bienvenue,
            <span class="bold">
              <s:property escape="false" value="username" />
            </span>
                !
            </div>
        </div>
    </div>
</div>
<div class="content">
    <div class="scheduler" id="scheduler">
        <div class="leftcol">
            <div class="minical" id="minical">
            </div>
            <div class="filter" id="filter">
                <div class="f_header">
                    Users
                </div>
                <div class="f_list">
                    <table id="users">
                        <s:iterator value="users" status="userStatus">
                            <tr class="
<s:if test="#userStatus.even == true">
even
</s:if>
<s:else>
odd
</s:else>
">
                                <td class="checkbox">
                                    <input type="checkbox" id="user_
<s:property value="id"/>
" checked onchange="update_config();" />
                                </td>
                                <td class="last">
                                    <label for="user_
<s:property value="id" />
">
                                        <s:property value="name" />
                                    </label>
                                </td>
                            </tr>
                        </s:iterator>
                    </table>
                </div>
            </div>
        </div>
        <div class="rightcol">
            <s:property escape="false" value="planner" />
        </div>
    </div>
</div>

<script>
    function update_config() {
        var coll = [];
        var inputs = document.getElementById("users").getElementsByTagName("input");
        var labels = document.getElementById("users").getElementsByTagName("label");
        for (var i = 0; i < inputs.length; i++) {
            if (!inputs[i].checked) continue;

            var id = inputs[i].id.replace("user_", "");
            var name = labels[i].innerHTML;

            coll.push({key:id, label:name});
        }
        if (coll.length > 0) {
            scheduler._props.units.options = coll;
            scheduler.callEvent("onOptionsLoad", []);
            scheduler.setCurrentView();
        }
    }
</script>
<%@ include file="footer.jsp" %>

