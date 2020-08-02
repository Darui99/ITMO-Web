<#-- @ftlvariable name="user_id" type="java.lang.Long" -->
<#import "common.ftl" as c/>

<@c.page>
    <#if user_id?? && user_id?is_number>
        <#assign user=c.findBy(users, "id", user_id)!/>
        <#if user.id??>
            <ul>
                <#list posts?reverse as p>
                    <#if p.user_id == user.id>
                        <@c.display_post p true></@c.display_post>
                    </#if>
                </#list>
            </ul>
        <#else>
            <h1>No such user</h1>
        </#if>
    <#else>
        <h1>No such user</h1>
    </#if>
</@c.page>
