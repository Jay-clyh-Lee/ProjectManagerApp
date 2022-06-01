package com.example.demo.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="projects")
public class Project {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="Title is required!")
    private String title;
    
    @NotEmpty(message="Description is required!")
    @Size(min=3, message="Description must be at least 3 characters long")
    private String description;
    
    @Future
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date dueDate;
	
	@OneToMany(mappedBy="project", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Task> task;
	
	@OneToMany(mappedBy="project", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	private List<UserProject> UserProject;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id") //{entity}_{attribute}
	private User user;
	
	@Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
        
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
	
	// getter setter
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}	
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public List<Task> getTask() {
		return task;
	}
	public void setTask(List<Task> task) {
		this.task = task;
	}
	public List<UserProject> getUserProject() {
		return UserProject;
	}
	public void setUserProject(List<UserProject> userProject) {
		UserProject = userProject;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	// constructor
	public Project () {}
}