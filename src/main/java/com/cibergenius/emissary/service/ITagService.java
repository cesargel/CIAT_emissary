package com.cibergenius.emissary.service;

import java.util.Set;

import com.cibergenius.emissary.entities.TagModel;

public interface ITagService {
	public Set<TagModel> obtenerRegisteredTag(String tagId);
	public void guardarRegisteredTags(Set<TagModel> registeredTagsModel);
}
