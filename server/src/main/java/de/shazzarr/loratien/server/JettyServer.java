package de.shazzarr.loratien.server;

import java.io.IOException;
import java.lang.invoke.MethodHandles;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.shazzarr.loratien.server.servlet.MainApplication;

public class JettyServer {
	private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	private Server server;

	public void start() {
		final int jettyMaxThreads = Loratien.properties.getInt("jettyMaxThreads");
		final int jettyMinThreads = Loratien.properties.getInt("jettyMinThreads");
		final int jettyIdleTimeout = Loratien.properties.getInt("jettyIdleTimeout");
		server = new Server(new QueuedThreadPool(jettyMaxThreads, jettyMinThreads, jettyIdleTimeout));

		final ServerConnector connector = new ServerConnector(server);
		final int port = Loratien.properties.getInt("jettyServerPort");
		connector.setPort(port);
		server.addConnector(connector);

		final ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
		contextHandler.setContextPath("/");

		try {
			final ServletHolder servletHolder = new ServletHolder(
					new ServletContainer(ResourceConfig.forApplication(new MainApplication())));
			contextHandler.addServlet(servletHolder, "/api/*");
		} catch (final IOException e) {
			e.printStackTrace();
		}
		server.setHandler(contextHandler);

		try {
			server.start();
			log.info("The server is listening on port " + port + ".");
			log.info("The server has been started successfully.");
		} catch (final Exception e) {
			log.error(e.getMessage());
		}
	}
}