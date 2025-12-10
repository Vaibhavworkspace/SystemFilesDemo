package com.model;

import java.util.HashMap;
import java.util.Map;

import com.model.inter.FileSystemEntity;

public class Folder implements FileSystemEntity {

	private final String name;
	private FileSystemEntity parent;
	private final Map<String, FileSystemEntity> children = new HashMap<>();

	public Folder(String name, FileSystemEntity parent) {
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
		return children.values().stream().mapToInt(FileSystemEntity::getSize).sum();
	}

	public Map<String, FileSystemEntity> getChildren() {
		return children;
	}

}
