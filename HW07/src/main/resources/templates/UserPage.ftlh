<#-- @ftlvariable name="displaying_user" type="ru.itmo.wp.domain.User[]" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="users datatable">
        <#if displaying_user??>
            <div class="caption">User</div>
            <table>
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Login</th>
                    <th>Created</th>
                </tr>
                </thead>
                <tbody>
                    <tr>
                        <td class="id">${displaying_user.id}</td>
                        <td class="login">${displaying_user.login}</td>
                        <td class="creationTime">${displaying_user.creationTime}</td>
                    </tr>
                </tbody>
            </table>
        <#else>
            <h1>No such user</h1>
        </#if>
    </div>
</@c.page>
