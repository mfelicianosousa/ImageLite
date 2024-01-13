package br.com.mfsdevsytem.imageapi.domain.service;

import java.util.Optional;

import br.com.mfsdevsytem.imageapi.domain.entity.Image;

public interface ImageService {

	Image save(Image image);
	Optional<Image> getById(String id);
}
