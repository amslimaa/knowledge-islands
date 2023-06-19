package br.com.gitanalyzer.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FilteringProjectsDTO {
	
	@NotBlank
	private String folderPath;
	private Integer numberOfYears;
}
