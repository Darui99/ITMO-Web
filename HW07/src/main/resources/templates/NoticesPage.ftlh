<#-- @ftlvariable name="noticeForm" type="ru.itmo.wp.domain.Notice" -->
<#import "/spring.ftl" as spring>
<#import "commons.ftlh" as c>

<@c.page>
    <div class="notification form-box">
        <div class="header">Notice</div>
        <div class="body">
            <form method="post" action="">
                <input type="hidden" name="action" value="notice"/>
                <div class="field">
                    <div class="name">
                        <label for="content">Content</label>
                    </div>
                    <div class="value">
                        <textarea id="content" name="content">${noticeForm.content!}</textarea>
                    </div>
                    <@c.error "noticeForm.content"/>
                </div>
                <div class="button-field">
                    <input type="submit" value="Notice">
                </div>
            </form>
        </div>
    </div>
</@c.page>
