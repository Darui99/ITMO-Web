<#macro header>
<header>
    <a href="/"><img src="/img/logo.png" alt="Codeforces" title="Codeforces"/></a>
    <div class="languages">
        <a href="#"><img src="/img/gb.png" alt="In English" title="In English"/></a>
        <a href="#"><img src="/img/ru.png" alt="In Russian" title="In Russian"/></a>
    </div>
    <div class="enter-or-register-box">
        <#if user??>
            <@userlink user=user nameOnly=true/>
            |
            <a href="#">Logout</a>
        <#else>
            <a href="#">Enter</a>
            |
            <a href="#">Register</a>
        </#if>
    </div>
    <nav>
        <ul>
            <li>
                <@headhref "/index" "Index"/>
            </li>
            <li>
                <@headhref "/misc/help" "Help"/>
            </li>
            <li>
                <@headhref "/users" "Users"/>
            </li>
        </ul>
    </nav>
</header>
</#macro>

<#macro headhref uri text>
    <#if cur_uri?? && uri == cur_uri>
        <a href="${uri}" style="border-bottom: 3px solid #3B5998;">${text}</a>
    <#else>
        <a href="${uri}">${text}</a>
    </#if>
</#macro>

<#macro sidebar>
<aside>
    <#list posts?reverse as p>
        <section>
            <div class="header">
                Post #${p.id}
            </div>
            <div class="body">
                <@c.display_post p true></@c.display_post>
            </div>
            <div class="footer">
                <a href="/post?post_id=${p.id}">View all</a>
            </div>
        </section>
    </#list>
</aside>
</#macro>

<#macro footer>
<footer>
    <a href="#">Codeforces</a> &copy; 2010-2019 by Mike Mirzayanov
</footer>
</#macro>

<#macro page>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Codeforces</title>
    <link rel="stylesheet" type="text/css" href="/css/normalize.css">
    <link rel="stylesheet" type="text/css" href="/css/style.css">
    <link rel="icon" href="/favicon.ico" type="image/x-icon"/>
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" type="text/css" href="/css/post.css">
</head>
<body>
    <@header/>
    <div class="middle">
        <@sidebar/>
        <main>
            <#nested/>
        </main>
    </div>
    <@footer/>
</body>
</html>
</#macro>

<#macro userlink user nameOnly>
    <#if nameOnly>
        <a href="/user?handle=${user.handle}">${user.name}</a>
    <#else>
        <a href="/user?handle=${user.handle}" style="color: ${user.color}; text-decoration: none; font-style: italic">${user.handle}</a>
    </#if>
</#macro>

<#function get_prev items key id>
    <#assign prev=""/>
    <#list items as item>
        <#if item[key]==id>
            <#return prev/>
        </#if>
        <#assign prev=item/>
    </#list>
</#function>

<#function findBy items key id>
    <#list items as item>
        <#if item[key]==id>
            <#return item/>
        </#if>
    </#list>
</#function>

<#macro display_post post need_cut>
    <#assign author=findBy(users, "id", post.user_id)!/>
    <article>
        <div class="title">${post.title}</div>
        <div class="information">By <@userlink author false/></div>
        <div class="body">
            <#if need_cut && (post.text?length > 250)>
                ${post.text?substring(0, 250)}...
            <#else>
                ${post.text?substring(0, post.text?length)}
            </#if>
        </div>
        <div class="footer">
            <div class="left">
                <img src="/img/voteup.png" title="Vote Up" alt="Vote Up"/>
                <span class="positive-score">+239</span>
                <img src="/img/votedown.png" title="Vote Down" alt="Vote Down"/>
            </div>
            <div class="right">
                <img src="/img/date_16x16.png" title="Publish Time" alt="Publish Time"/>
                3 days ago
                <img src="/img/comments_16x16.png" title="Comments" alt="Comments"/>
                <a href="#">97</a>
            </div>
        </div>
    </article>
</#macro>