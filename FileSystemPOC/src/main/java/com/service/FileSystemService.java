package com.service;

import java.util.Map;
import java.util.Optional;

import com.model.Drive;
import com.model.Folder;
import com.model.TextFile;
import com.model.ZipFile;
import com.model.inter.FileSystemEntity;
import com.repository.InMemoryFileSystemRepo;

public class FileSystemService {

	private final InMemoryFileSystemRepo repo = new InMemoryFileSystemRepo();

	// CREATE DRIVE
	public String createDrive(String name) {
		repo.createDrive(name);
		return "Drive created: " + name;
	}

	// CREATE ENTITY
	public String create(String parentPath, String name, String type) {
		Optional<FileSystemEntity> parentOpt = repo.find(parentPath);
		if (parentOpt.isEmpty())
			return "Parent not found";

		FileSystemEntity parent = parentOpt.get();
		Map<String, FileSystemEntity> children = getChildren(parent);

		if (children.containsKey(name))
			return "Entity already exists";

		FileSystemEntity entity = switch (type.toLowerCase()) {
		case "folder" -> new Folder(name, parent);
		case "text" -> new TextFile(name, parent);
		case "zip" -> new ZipFile(name, parent);
		default -> throw new RuntimeException("Invalid type");
		};

		children.put(name, entity);
		return "Created: " + entity.getPath();
	}

	// DELETE
	public String delete(String path) {
		System.out.println("FileSystemService :: delete Method Call");
		Optional<FileSystemEntity> opt = repo.find(path);
		if (opt.isEmpty())
			return "Path Not found";

		FileSystemEntity entity = opt.get();
		if (entity.getParent() == null)
			return "Cannot delete drive";

		Map<String, FileSystemEntity> siblings = getChildren(entity.getParent());
		siblings.remove(entity.getName());

		return "Deleted: " + path;
	}

	// MOVE
	public String move(String source, String destination) {
		System.out.println("FileSystemService :: move Method Call");
		Optional<FileSystemEntity> srcOpt = repo.find(source);
		Optional<FileSystemEntity> dstOpt = repo.find(destination);

		if (srcOpt.isEmpty() || dstOpt.isEmpty())
			return "Source or destination not found";

		FileSystemEntity src = srcOpt.get();
		FileSystemEntity dst = dstOpt.get();

		Map<String, FileSystemEntity> oldChildren = getChildren(src.getParent());
		oldChildren.remove(src.getName());

		Map<String, FileSystemEntity> newChildren = getChildren(dst);
		newChildren.put(src.getName(), src);
		src.setParent(dst);
		return "Moved: " + src.getPath();
	}

	// WRITE TO FILE
	public String write(String path, String content) {
		System.out.println("FileSystemService :: write Method Call");
		Optional<FileSystemEntity> opt = repo.find(path);
		if (opt.isEmpty())
			return "Not found";
		if (!(opt.get() instanceof TextFile tf))
			return "Not a text file";
		tf.write(content);
		return "Updated content";
	}

	// SIZE
	public int getSize(String path) {
		return repo.find(path).map(FileSystemEntity::getSize).orElse(-1);
	}

	private Map<String, FileSystemEntity> getChildren(FileSystemEntity e) {
		if (e instanceof Drive d)
			return d.getChildren();
		if (e instanceof Folder f)
			return f.getChildren();
		if (e instanceof ZipFile z)
			return z.getChildren();
		throw new RuntimeException("Cannot contain children");
	}

	public boolean exists(String path) {
		return repo.find(path).isPresent();
	}
}
