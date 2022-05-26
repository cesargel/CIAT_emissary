package com.cibergenius.emissary.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import com.cibergenius.emissary.entities.TagModel;
import com.cibergenius.emissary.repository.ITagRepository;

@Service
public class TagService implements ITagService {

    @Autowired
    private ITagRepository tagRepository;

    @Override
    public Set<TagModel> obtenerRegisteredTag(@Nullable String tagId) {
        if (tagId == null || tagId.isEmpty() || tagId.equalsIgnoreCase("")) {
            return new HashSet<TagModel>((Collection<? extends TagModel>) tagRepository.findAll());
        }
        return new HashSet<TagModel>(tagRepository.findByEPC(tagId));
    }

    @Override
    public void guardarRegisteredTags(Set<TagModel> registeredTagsModel) {
        try {
            tagRepository.saveAll(registeredTagsModel);
        } catch (Exception e) {
            throw e;
        }
    }

}
