package com.jisr.entity;

import com.jisr.entity.core.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "announcements")
public class Announcement extends AuditableEntity<Long> {

	private static final long serialVersionUID = 1L;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "content", nullable = false)
	private String content;

	@Lob
	@Column(name = "image")
	private byte[] image;

}
