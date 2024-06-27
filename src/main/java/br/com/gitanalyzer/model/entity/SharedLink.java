package br.com.gitanalyzer.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.gitanalyzer.model.Commit;
import br.com.gitanalyzer.model.github_openai.FileLinkAuthor;
import br.com.gitanalyzer.model.github_openai.enums.SharedLinkSourceType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
public class SharedLink {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String link;
	@Lob
	@Column(columnDefinition="TEXT")
	private String textMatchFragment;
	@Lob
	@Column(columnDefinition="TEXT")
	private String openAiFullJson;
	@JsonIgnore
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private ChatgptConversation conversation;
	@OneToOne(cascade = {CascadeType.PERSIST})
	private Commit commitThatAddedTheLink;
	@OneToMany(mappedBy="sharedLink", cascade = CascadeType.REMOVE)
	private List<FileLinkAuthor> filesLinkAuthor;
	@Enumerated(EnumType.STRING)
	private SharedLinkSourceType type;
	@ManyToOne
	private GitRepository repository;

	public SharedLink() {
		this.filesLinkAuthor = new ArrayList<>();
	}

	public SharedLink(String link, String textMatchFragment) {
		super();
		this.link = link;
		this.textMatchFragment = textMatchFragment;
	}

	public void addFileLinkAuthor(FileLinkAuthor fileLinkAuthor) {
		fileLinkAuthor.setSharedLink(this);
		filesLinkAuthor.add(fileLinkAuthor);
	}
	public SharedLink(String link, String textMatchFragment, ChatgptConversation conversation, String openAiFullJson) {
		super();
		this.link = link;
		this.textMatchFragment = textMatchFragment;
		this.conversation = conversation;
		this.openAiFullJson = openAiFullJson;
	}
}
