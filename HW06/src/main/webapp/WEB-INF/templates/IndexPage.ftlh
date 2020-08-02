<#-- @ftlvariable name="articles" type="ru.itmo.wp.model.domain.Article[]" -->
<#-- @ftlvariable name="users" type="ru.itmo.wp.model.domain.User[]" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="articles">
        <ul>
            <template>
                <article>
                    <div class="title"></div>
                    <div class="information"></div>
                    <div class="body"></div>
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
            </template>
        </ul>
    </div>

    <script>
        $(function () {
            ajax({
                url: "/index",
                data: {
                    action: "findAllArticles"
                },
                success: function(response) {
                    response["articles"].forEach(function(article) {
                        var login = "";
                        response["users"].forEach(function(user) {
                            if (article["userId"] === user["id"]) {
                                login = user["login"];
                            }
                        });
                        var $cur = $($(".articles template").prop("content")).clone();
                        $cur.find(".title").text(article["title"]);
                        $cur.find(".body").text(article["text"]);
                        $cur.find(".information").text("By " + login);
                        if (!article["hidden"]) {
                            $(".articles ul").append($cur);
                        }
                    });
                }
            });
        })
    </script>

</@c.page>
