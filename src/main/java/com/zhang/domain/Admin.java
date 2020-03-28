package com.zhang.domain;

import java.util.Date;

/**
 * @author prayers
 * @date 2019/10/29 20:52
 */
public class Admin {


	private Integer admin_id;
	private String admin_code;
	private String admin_name;
	private String admin_password;
	private String admin_state;
	private String admin_stat;
	private String title;
	private String role;
	private Date publishedDate;
	private String content;

	public String getEntry_time() {
		return entry_time;
	}

	public void setEntry_time(String entry_time) {
		this.entry_time = entry_time;
	}

	@Override
	public String toString() {
		return "Admin{" +
				"admin_id=" + admin_id +
				", admin_code='" + admin_code + '\'' +
				", admin_name='" + admin_name + '\'' +
				", admin_password='" + admin_password + '\'' +
				", admin_state='" + admin_state + '\'' +
				", admin_stat='" + admin_stat + '\'' +
				", title='" + title + '\'' +
				", role='" + role + '\'' +
				", publishedDate=" + publishedDate +
				", content='" + content + '\'' +
				", entry_time='" + entry_time + '\'' +
				", time=" + time +
				'}';
	}

	private String entry_time;
	private Date time;

	public Integer getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(Integer admin_id) {
		this.admin_id = admin_id;
	}

	public String getAdmin_code() {
		return admin_code;
	}

	public void setAdmin_code(String admin_code) {
		this.admin_code = admin_code;
	}

	public String getAdmin_name() {
		return admin_name;
	}

	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}

	public String getAdmin_password() {
		return admin_password;
	}

	public void setAdmin_password(String admin_password) {
		this.admin_password = admin_password;
	}

	public String getAdmin_state() {
		return admin_state;
	}

	public void setAdmin_state(String admin_state) {
		this.admin_state = admin_state;
	}

	public String getAdmin_stat() {
		return admin_stat;
	}

	public void setAdmin_stat(String admin_stat) {
		this.admin_stat = admin_stat;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}
