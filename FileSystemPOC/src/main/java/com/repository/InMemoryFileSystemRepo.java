package com.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.model.Drive;
import com.model.Folder;
import com.model.ZipFile;
import com.model.inter.FileSystemEntity;

public class InMemoryFileSystemRepo {

	private final Map<String, Drive> drives = new HashMap<>();

	public Drive createDrive(String name) {
		Drive d = new Drive(name);
		drives.put(name, d);
		return d;
	}

	public Optional<FileSystemEntity> find(String path) {
		String[] parts = path.split("\\\\");
		Drive drive = drives.get(parts[0]);
		if (drive == null)
			return Optional.empty();

		FileSystemEntity current = drive;
		for (int i = 1; i < parts.length; i++) {
			if (parts[i].isEmpty())
				continue;

			Map<String, FileSystemEntity> children = switch (current) {
			case Drive d -> d.getChildren();
			case Folder f -> f.getChildren();
			case ZipFile z -> z.getChildren();
			default -> null;
			};

			if (children == null)
				return Optional.empty();

			current = children.get(parts[i]);
			if (current == null)
				return Optional.empty();
		}
		return Optional.of(current);
	}

	public Map<String, Drive> getDrives() {
		return drives;
	}

}
