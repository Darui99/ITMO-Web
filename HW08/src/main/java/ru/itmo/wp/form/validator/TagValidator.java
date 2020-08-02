package ru.itmo.wp.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.itmo.wp.util.TagHelper;

import java.util.HashSet;

@Component
public class TagValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return String.class.equals(clazz);
    }

    private boolean isCorrectTag(String word) {
        return word.matches("[a-zA-Z]{1,50}");
    }

    public void validate(Object target, Errors errors) {
        if (!errors.hasErrors()) {
            String mergedTags = (String) target;
            HashSet<String> tags = TagHelper.splitByWhitespaces(mergedTags);
            for (String tag : tags) {
                if (!isCorrectTag(tag)) {
                    errors.reject("tags", "String contains invalid tag");
                    return;
                }
            }

        }
    }
}
