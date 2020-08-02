<#-- @ftlvariable name="articles" type="ru.itmo.wp.model.domain.Article[]" -->
<#-- @ftlvariable name="user" type="ru.itmo.wp.model.domain.User" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="users datatable">
        <div class="caption">My articles</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Title</th>
                <th>Hide/Show</th>
            </tr>
            </thead>
            <tbody>
            <#list articles as a>
                <#if a.userId == user.id>
                    <tr>
                        <td>${a.id}</td>
                        <td>${a.title}</td>
                        <td>
                            <div class="button-field">
                                <#if a.hidden>
                                    <input type="submit" value="Show" id="${a.id}">
                                <#else>
                                    <input type="submit" value="Hide" id="${a.id}">
                                </#if>
                            </div>
                        </td>
                    </tr>
                </#if>
            </#list>
            </tbody>
        </table>
    </div>

    <script>
        $(function () {
            $(".button-field input").click(function () {
                var hidden;
                if ($(this).attr("value") === "Show") {
                    $(this).attr("value", "Hide");
                    hidden = false;
                }
                else {
                    $(this).attr("value", "Show");
                    hidden = true;
                }
                ajax({
                    data: {
                        action: "setHidden",
                        articleId: $(this).attr("id"),
                        hiddenValue: hidden
                    },
                    success: function(response) {}
                });
                return false;
            });
        })
    </script>

</@c.page>
