package com.model;

import com.model.inter.FileSystemEntity;

public class TextFile implements FileSystemEntity {

	private final String name;
	private FileSystemEntity parent;
	private String content = "";

	public TextFile(String name, FileSystemEntity parent) {
		this.name = name;
		this.parent = parent;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public FileSystemEntity getParent() {
		return parent;
	}

	@Override
	public void setParent(FileSystemEntity parent) {
		this.parent = parent;
	}

	@Override
	public String getPath() {
		return parent.getPath() + name + "/";
	}

	@Override
	public int getSize() {
		return content.length();
	}

	public void write(String text) {
		this.content = text;
	}

	public String getContent() {
		return content;
	}

}
