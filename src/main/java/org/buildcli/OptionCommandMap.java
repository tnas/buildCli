package org.buildcli;

import java.util.HashMap;

import org.buildcli.core.ProjectCompiler;
import org.buildcli.core.ProjectInitializer;
import org.buildcli.core.ProjectRunner;
import org.buildcli.core.ProjectUpdater;
import org.buildcli.exception.ThrowingComandExecutorWrapper;
import org.buildcli.utils.CodeDocumenter;
import org.buildcli.utils.EnvironmentConfigManager;
import org.buildcli.utils.PomUtils;

public class OptionCommandMap extends HashMap<String, CommandExecutor> {

	private static final long serialVersionUID = 1L;

	public OptionCommandMap(OptionCommand optionCommand) {

		super();
		var wrapper = new ThrowingComandExecutorWrapper();

		this.put("-i", () -> wrapper.wrap(() -> new ProjectInitializer().initializeProject()));
		this.put("--init", () -> wrapper.wrap(() -> new ProjectInitializer().initializeProject()));
		this.put("-c", () -> new ProjectCompiler().compileProject());
		this.put("--compile", () -> new ProjectCompiler().compileProject());
		this.put("--add-dependency", () -> PomUtils.addDependencyToPom(optionCommand.dependency));
		this.put("--rm-dependency", () -> PomUtils.rmDependencyToPom(optionCommand.rmDependency));
		this.put("-p", () -> ProjectInitializer.createProfileConfig(optionCommand.profile));
		this.put("--profile", () -> ProjectInitializer.createProfileConfig(optionCommand.profile));
		this.put("-e", () -> EnvironmentConfigManager.setEnvironment(optionCommand.environment));
		this.put("--set-environment", () -> EnvironmentConfigManager.setEnvironment(optionCommand.environment));
		this.put("--run", () -> new ProjectRunner().runProject());
		this.put("-d", () -> CodeDocumenter.getDocumentationFromOllama(optionCommand.fileToDocument));
		this.put("--document-code", () -> CodeDocumenter.getDocumentationFromOllama(optionCommand.fileToDocument));
		this.put("-u", () -> new ProjectUpdater().execute());
		this.put("--update", () -> new ProjectUpdater().execute());
		this.put("--update-now", () -> new ProjectUpdater().updateNow(true).execute());
	}

}
