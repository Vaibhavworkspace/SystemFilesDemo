package com.model;

import java.util.HashMap;
import java.util.Map;

import com.model.inter.FileSystemEntity;

public class Drive implements FileSystemEntity {

	private final String name;
	private final Map<String, FileSystemEntity> children = new HashMap<>();

	public Drive(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public FileSystemEntity getParent() {
		return null;
	}

	@Override
	public void setParent(FileSystemEntity parent) {
		/* drives have no parent */ 
		}

	@Override
	public String getPath() { 
		return name + "/";
	}

	@Override
	public int getSize() {
		return children.values().stream().mapToInt(FileSystemEntity::getSize).sum();
	}

	public Map<String, FileSystemEntity> getChildren() {
		return children;
	}
}
