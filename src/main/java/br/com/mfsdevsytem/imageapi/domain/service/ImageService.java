package br.com.mfsdevsytem.imageapi.domain.service;

import java.util.List;
import java.util.Optional;

import br.com.mfsdevsytem.imageapi.domain.entity.Image;
import br.com.mfsdevsytem.imageapi.domain.enums.ImageExtension;

public interface ImageService {

	Image save(Image image);
	Optional<Image> getById(String id);
	List<Image> search(ImageExtension extension, String query);
}
