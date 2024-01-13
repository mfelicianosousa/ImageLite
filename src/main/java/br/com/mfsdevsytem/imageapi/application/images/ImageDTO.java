package br.com.mfsdevsytem.imageapi.application.images;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import br.com.mfsdevsytem.imageapi.domain.entity.Image;
import br.com.mfsdevsytem.imageapi.domain.entity.Image.Builder;
import br.com.mfsdevsytem.imageapi.domain.enums.ImageExtension;


public class ImageDTO {

	private String url;
	private String name;
	private String extension;
	private Long size;
	private LocalDate uploadDate;
	
	private ImageDTO() {
		
	}

	public ImageDTO(String url, String name, String extension, Long size, LocalDate uploadDate) {
		this.url = url;
		this.name = name;
		this.extension = extension;
		this.size = size;
		this.uploadDate = uploadDate;
	}
	
	 // Método estático para criar uma instância do builder
    public static Builder builder() {
        return new Builder();
    }
	
	// Builder interno para construir a instância de Image
    public static class Builder {
    	private String url;
        private String name;
        private Long size;
        private String extension;
        private LocalDate uploadDate;

        // Construtor privado
        private Builder() {
        }

        // Métodos para configurar os campos do builder

        public Builder url(String url) {
            this.url = url;
             return this;
        }
        
        public Builder name(String name) {
           this.name = name;
            return this;
        }

        public Builder size(Long size) {
            this.size = size;
            return this;
        }

        public Builder extension(String extension) {
            this.extension = extension;
            return this;
        }

        public Builder uploadDate(LocalDate uploadDate) {
            this.uploadDate = uploadDate;
            return this;
        }

        // Método para construir a instância de ImageDTO
        public ImageDTO build() {
            ImageDTO image = new ImageDTO();
            image.url = this.url; 
            image.name = this.name;
            image.size = this.size;
            image.extension = this.extension;
            image.uploadDate = this.uploadDate;
            return image;
        }
    }

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public LocalDate getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(LocalDate uploadDate) {
		this.uploadDate = uploadDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(url);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ImageDTO other = (ImageDTO) obj;
		return Objects.equals(url, other.url);
	}
	
}
