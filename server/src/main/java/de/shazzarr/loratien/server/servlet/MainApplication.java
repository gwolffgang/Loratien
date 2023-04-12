package de.shazzarr.loratien.server.servlet;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;

import de.shazzarr.loratien.server.servlet.menu.MenuServlet;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
public class MainApplication extends Application {
	private final Set<Object> singletons = new HashSet<>();
	private final Set<Class<?>> classes = new HashSet<>();

	public MainApplication() throws IOException {
		singletons.add(new MenuServlet());
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}

	@Override
	public Set<Class<?>> getClasses() {
		return classes;
	}
}