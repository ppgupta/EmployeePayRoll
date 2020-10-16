package com.bridgelabz.EmployeePayRoll;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.WatchKey;
import java.util.HashMap;
import java.util.Map;

public class WatchService {
	private final WatchService watcher;
	private final Map<WatchKey, Path> directoryWatchers;

	public WatchService(Path directoryPath) throws IOException {
		this.watcher = FileSystems.getDefault().newWatchService();
		this.directoryWatchers = new HashMap<WatchKey,Path>();
		scanAndRegisterDirectories(directoryPath);
	}

	private void scanAndRegisterDirectories(final Path Start) throws IOException {
		Files.walkFileTree(Start, (simpleFileVisitor)preVisitDirectory(directoryWatchers,attrs)->{
			registerDirWatchers(directoryWatchers);
			return FileVisitResult.CONTINUE;
		});
	}
}
