package com.model.inter;

public interface FileSystemEntity {

	String getName();
	FileSystemEntity getParent();
	void setParent(FileSystemEntity parent);
	String getPath();
	int getSize();
}
