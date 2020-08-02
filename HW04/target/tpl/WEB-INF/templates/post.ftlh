<#-- @ftlvariable name="post_id" type="java.lang.Long" -->
<#import "common.ftl" as c/>

<@c.page>
    <#if post_id?? && post_id?is_number>
        <#assign post=c.findBy(posts, "id", post_id)!/>
        <#if post.id??>
            <@c.display_post post false/>
        <#else>
            <h1>No such post</h1>
        </#if>
    <#else>
        <h1>No such post</h1>
    </#if>
</@c.page>
