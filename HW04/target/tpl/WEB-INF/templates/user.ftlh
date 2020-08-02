<#-- @ftlvariable name="handle" type="java.lang.String" -->
<#-- @ftlvariable name="user_id" type="java.lang.Long" -->
<#import "common.ftl" as c/>

<link rel="stylesheet" type="text/css" href="/css/user.css">

<@c.page>
    <#if handle??>
        <#assign viewedUser=c.findBy(users, "handle", handle)!/>
        <#assign good_param = true/>
    <#elseif user_id?? && user_id?is_number>
        <#assign viewedUser=c.findBy(users, "id", user_id)!/>
        <#assign good_param = true/>
    </#if>

    <#if good_param?? && viewedUser.id??>
        <div class="user-info">
            <div class="nickname">
                <@c.userlink user=viewedUser nameOnly=false/>
            </div>
            <ul>
                <li>id: ${viewedUser.id}</li>
                <li>handle: ${viewedUser.handle}</li>
                <li>name: ${viewedUser.name}</li>
                <li> <a href="/posts?user_id=${viewedUser.id}">posts</a> </li>
            </ul>
            <img src="../img/user.jpg" alt="user-image">
        </div>
        <div class="arrows">
            <#assign prev=c.get_prev(users, "handle", viewedUser.handle)!/>
            <#assign next=c.get_prev(users?reverse, "handle", viewedUser.handle)!/>
            <#if prev != "">
                <a href="/user?handle=${prev.handle}" style="color: blue; text-decoration: none;">←</a>
            <#else>
                <a style="color: gray;">←</a>
            </#if>
            <#if next != "">
                <a href="/user?handle=${next.handle}" style="color: blue; text-decoration: none;">→</a>
            <#else>
                <a style="color: gray;">→</a>
            </#if>
        </div>
    <#else>
        <h1>No such user</h1>
    </#if>
</@c.page>