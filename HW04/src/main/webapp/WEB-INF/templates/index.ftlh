<#import "common.ftl" as c/>

<@c.page>
<ul>
    <#list posts?reverse as p>
            <@c.display_post p true></@c.display_post>
    </#list>
</ul>
</@c.page>
