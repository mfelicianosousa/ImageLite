package br.com.mfsdevsytem.imageapi.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mfsdevsytem.imageapi.domain.entity.Image;

public interface ImageRepository extends JpaRepository<Image,String>{

	
}
