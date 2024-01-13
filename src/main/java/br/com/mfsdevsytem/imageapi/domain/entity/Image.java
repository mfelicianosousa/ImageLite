package br.com.mfsdevsytem.imageapi.domain.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import br.com.mfsdevsytem.imageapi.domain.enums.ImageExtension;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "image")
public class Image implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	@Column(name="image_name")
	private String name;
	@Column
	private Long size;
	
	@Enumerated(EnumType.STRING)
	private ImageExtension extension;
	
	@Column
	@CreatedDate
	private LocalDateTime uploadDate;
	
	@Column
	private String tags;
	
	@Column
	@Lob
	private byte[] file;

	public Image() {
		
	}
	public Image(String id, String name, Long size, ImageExtension extension, LocalDateTime uploadDate, String tags,
			byte[] file) {
		this.id = id;
		this.name = name;
		this.size = size;
		this.extension = extension;
		this.uploadDate = uploadDate;
		this.tags = tags;
		this.file = file;
	}
	 // Método estático para criar uma instância do builder
    public static Builder builder() {
        return new Builder();
    }
	
	// Builder interno para construir a instância de Image
    public static class Builder {
        private String id;
        private String name;
        private Long size;
        private ImageExtension extension;
        private LocalDateTime uploadDate;
        private String tags;
        private byte[] file;

        // Construtor privado
        private Builder() {
        }

        // Métodos para configurar os campos do builder
        public Builder id(String id) {
            this.id = id;
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

        public Builder extension(ImageExtension extension) {
            this.extension = extension;
            return this;
        }

        public Builder uploadDate(LocalDateTime uploadDate) {
            this.uploadDate = uploadDate;
            return this;
        }

        public Builder tags(String tags) {
            this.tags = tags;
            return this;
        }

        public Builder file(byte[] file) {
            this.file = file;
            return this;
        }

       
        // Método para construir a instância de Image
        public Image build() {
            Image image = new Image();
            image.id = this.id;
            image.name = this.name;
            image.size = this.size;
            image.extension = this.extension;
            image.uploadDate = this.uploadDate;
            image.tags = this.tags;
            image.file = this.file;
            return image;
        }
    }
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getSize() {
		return size;
	}
	public void setSize(Long size) {
		this.size = size;
	}
	public ImageExtension getExtension() {
		return extension;
	}
	public void setExtension(ImageExtension extension) {
		this.extension = extension;
	}
	public LocalDateTime getUploadDate() {
		return uploadDate;
	}
	public void setUploadeDate(LocalDateTime uploadeDate) {
		this.uploadDate = uploadDate;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public byte[] getFile() {
		return file;
	}
	public void setFile(byte[] file) {
		this.file = file;
	}
	
	public String getFileName() {
		return getName().concat(".").concat(getExtension().name());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Image other = (Image) obj;
		return Objects.equals(id, other.id);
	}
	
}
