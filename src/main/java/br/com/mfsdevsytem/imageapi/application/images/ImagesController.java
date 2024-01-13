package br.com.mfsdevsytem.imageapi.application.images;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.mfsdevsytem.imageapi.domain.entity.Image;
import br.com.mfsdevsytem.imageapi.domain.service.ImageService;

@RestController
@RequestMapping("/v1/images")
public class ImagesController {

	private static final Logger logger = LoggerFactory.getLogger(ImagesController.class);

	private final ImageService service;
	private final ImageMapper mapper;
	
	public ImagesController(final ImageService service, ImageMapper mapper) {
		this.service = service;
		this.mapper= mapper;
	}


	@PostMapping
	public ResponseEntity save (
			@RequestParam("file") MultipartFile file,
			
			@RequestParam("name") String name,
			@RequestParam("tags") List<String> tags
	) throws IOException {
		logger.info("Imagem recebida name: {}, size: {} ", file.getOriginalFilename(), file.getSize());
	   // logger.info("Content Type: {}",file.getContentType());
	   // logger.info("Media Type: {}",MediaType.valueOf(file.getContentType()));
		
		Image image = mapper.mapToImage(file, name, tags);
		Image savedImage = service.save( image );	
		// http://localhost;8080/v1/images/uou.iouo.opoi.iiii.polp
		URI imageUri = buildImageURL( savedImage );
		
		return ResponseEntity.created( imageUri ).build();
	}
	
	private URI buildImageURL(Image image) {
		String imagePath = "/"+image.getId() ;
		return ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path( imagePath).build()
				.toUri();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<byte[]> getImage(@PathVariable("id") String id){
		var possibleImage = service.getById(id);
		if (possibleImage.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		var image = possibleImage.get();
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(image.getExtension().getMediaType());
	    headers.setContentLength(image.getSize());
	    // inline; filename="image.PNG"
	    headers.setContentDispositionFormData("inline; filename =\""+image.getFileName()+ "\"",image.getFileName());
	    
	    return new ResponseEntity<>(image.getFile(), headers, HttpStatus.OK);
	}
	
}
