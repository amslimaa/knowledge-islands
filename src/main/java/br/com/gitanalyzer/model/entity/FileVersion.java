package br.com.gitanalyzer.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileVersion implements Comparable<FileVersion>{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private double totalKnowledge = 0.0;
	@Transient
	private int numberActiveAuthor;
	@ManyToOne
	private File file;
	public FileVersion(File file) {
		super();
		this.file = file;
	}
	@Override
	public int compareTo(FileVersion other) {
		return Double.compare(other.totalKnowledge, this.totalKnowledge);
	}
}
