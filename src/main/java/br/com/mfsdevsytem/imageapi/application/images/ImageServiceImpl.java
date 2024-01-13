package br.com.mfsdevsytem.imageapi.application.images;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mfsdevsytem.imageapi.domain.entity.Image;
import br.com.mfsdevsytem.imageapi.domain.enums.ImageExtension;
import br.com.mfsdevsytem.imageapi.domain.service.ImageService;
import br.com.mfsdevsytem.imageapi.infra.repository.ImageRepository;

@Service
public class ImageServiceImpl implements ImageService{

	private final ImageRepository repository;
	
	public ImageServiceImpl(final ImageRepository repository) {
		this.repository = repository;
	}


	@Override
	@Transactional // operação de escrita no banco
	public Image save(Image image) {
		
		return repository.save( image );
	}


	@Override
	public Optional<Image> getById(String id) {
		
		return repository.findById(id);
	}


	@Override
	public List<Image> search(ImageExtension extension, String query) {

		return repository.findByExtensionAndNameOrTagsLike(extension, query);
	}

}
