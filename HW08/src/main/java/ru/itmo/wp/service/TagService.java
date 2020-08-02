package ru.itmo.wp.service;

import org.springframework.stereotype.Service;
import ru.itmo.wp.domain.Tag;
import ru.itmo.wp.repository.TagRepository;

import java.util.ArrayList;
import java.util.HashSet;

@Service
public class TagService {
    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public Tag findByName(String name) {
        return name == null ? null : tagRepository.findByName(name);
    }

    public ArrayList<Tag> createTags(HashSet<String> tags) {
        ArrayList<Tag> res = new ArrayList<>();
        for (String strTag : tags) {
            Tag tag = tagRepository.findByName(strTag);
            if (tag == null) {
                tag = new Tag();
                tag.setName(strTag);
                tagRepository.save(tag);
            }
            res.add(tag);
        }
        return res;
    }
}
