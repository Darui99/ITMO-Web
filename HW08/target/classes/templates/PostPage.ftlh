<#-- @ftlvariable name="displayingPost" type="ru.itmo.wp.domain.Post" -->
<#-- @ftlvariable name="commentForm" type="ru.itmo.wp.domain.Comment" -->

<#import "/spring.ftl" as spring>
<#import "commons.ftlh" as c>

<@c.page>
    <#if displayingPost??>
        <@c.post post=displayingPost/>
        <#if user??>
            <div class="form">
                <div class="header">Add comment</div>
                <div class="body">
                    <form method="post" action="">
                        <input type="hidden" name="action" value="registerComment"/>
                        <div class="field">
                            <div class="name">
                                <label for="text">Text</label>
                            </div>
                            <div class="value">
                                <textarea id="text" name="text">${commentForm.text!}</textarea>
                            </div>
                            <@c.error "commentForm.text"/>
                        </div>
                        <div class="button-field">
                            <input type="submit" value="Send">
                        </div>
                    </form>
                </div>
            </div>
        </#if>
        <#list displayingPost.comments as comment>
            <div class="comment">
                <a>${comment.user.login}: ${comment.text}</a>
            </div>
        </#list>
    <#else>
        <h1>No such post</h1>
    </#if>
</@c.page>
