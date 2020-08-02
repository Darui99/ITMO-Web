package ru.itmo.tpl.util;

import ru.itmo.tpl.Color;
import ru.itmo.tpl.model.Post;
import ru.itmo.tpl.model.User;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class DataUtil {
    private static final List<User> USERS = Arrays.asList(
            new User(1, "MikeMirayanov", "Mikhail Mirzayanov", Color.BLUE),
            new User(2, "tourist", "Genady Korotkevich", Color.RED),
            new User(3, "emusk", "Elon Musk", Color.GREEN),
            new User(5, "pashka", "Pavel Mavrin", Color.RED),
            new User(7, "geranazavr555", "Georgiy Nazarov", Color.BLUE),
            new User(11, "cannon147", "Erofey Bashunov", Color.GREEN)
    );

    private static final List<Post> POSTS = Arrays.asList(
            new Post(1, "О сорванном раунде 591", "Привет, Codeforces!\nК сожалению, недоброжелатели сорвали проведение раунда, сделав DDOS на нашу инфраструктуру. Ни координатор, ни авторы раунда не виноваты, что у вас не получилось полноценно принять участие в раунде. Пожалуйста, не минусуйте анонс раунда. Я думаю, что такая ситуация — дополнительный повод поддержать авторов. Они подготовили хорошие задачи!",
                    1),
            new Post(2, "XVIII Open Cup: GP of Gomel", "XVIII Open Cup Grand Prix of Gomel takes place on Sunday, February 18, 2018, at 11:00 AM Petrozavodsk time.\nYou need an Open Cup login to participate.\nI'm the writer of all the problems. Let's discuss them here after the contest!",
                    2),
            new Post(3, "Wavelet Tree problems", "Hello codeforces!\nI have started to learn about Wavelet trees. Could you please provide me some problems. I have not found any.\nThanks a lot!",
                    3)
    );

    private static List<User> getUsers() {
        return USERS;
    }

    private static List<Post> getPosts() {
        return POSTS;
    }

    public static void putData(Map<String, Object> data) {
        data.put("users", getUsers());
        data.put("posts", getPosts());

        for (User user : getUsers()) {
            if (data.get("logged_user_id") != null && data.get("logged_user_id").toString().equals(Long.toString(user.getId()))) {
                data.put("user", user);
            }
        }
    }
}
