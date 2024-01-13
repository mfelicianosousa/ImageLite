package br.com.mfsdevsytem.imageapi.infra.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.util.StringUtils;

import br.com.mfsdevsytem.imageapi.domain.entity.Image;
import br.com.mfsdevsytem.imageapi.domain.enums.ImageExtension;

public interface ImageRepository extends JpaRepository<Image,String>,
        JpaSpecificationExecutor<Image>{

	/**
	 * 
	 * @param extension
	 * @param query
	 * @return
	 * 
	 * SELECT * FROM IMAGE WHERE 1=1 AND EXTENSION = 'JPEG' AND
	 *  (NAME LIKE 'QUERY' OR TAGS LIKE 'QUERY' )
	 *
	 */
	default List<Image> findByExtensionAndNameOrTagsLike(ImageExtension extension, String query){
		// SELECT * FROM IMAGE WHERE 1=1
		Specification<Image> conjunction = (root, q, criteriaBuilder) -> criteriaBuilder.conjunction();
		
		Specification<Image> spec = Specification.where(conjunction);
		
		if (extension != null) {
			// AND EXTENSION = 'JPEG'
			Specification<Image> extensionEqual = (root, q, cb) -> cb.equal(root.get("extension"), extension);
	        spec = spec.and(extensionEqual)	;	
		}
		
		if(StringUtils.hasText(query)) {
			// AND (NAME LIKE 'QUERY' OR TAGS LIKE 'QUERY')
			Specification<Image> nameLike = (root, q, cb) -> cb.like( cb.upper(root.get("nome")), "%"+query.toUpperCase()+"%");
			Specification<Image> tagsLike = (root, q, cb) -> cb.like( cb.upper(root.get("tags")), "%"+query.toUpperCase()+"%");
	        
			Specification<Image> nameOrTagsLike = Specification.anyOf(nameLike, tagsLike);
			spec = spec.and(nameOrTagsLike)	;
		}
		
		return findAll( spec );
	}
	
}
