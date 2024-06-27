package br.com.gitanalyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gitanalyzer.model.entity.FileVersion;

@Repository
public interface FileVersionRepository extends JpaRepository<FileVersion, Long>{

}
