/*
 * Created on Jul 1, 2004
 *
 * To change the template for this generated file go to Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.fao.fi.figis.commonbuild.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import org.apache.tools.ant.BuildEvent;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.BuildListener;
import org.apache.tools.ant.BuildLogger;
import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;

/**
 * Version 4 because of new production server.
 * 
 * 
 * @author Calderini
 */
public class CommonBuildToolJava7 extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 6955001468778707292L;

	private ButtonGroup buttonGroup = new ButtonGroup();

	private JRadioButton deployRadioButton;

	private JRadioButton develRadioButton;

	private JRadioButton fiRadioButton;

	private JRadioButton validRadioButton;

	private JRadioButton validDvRadioButton;

	private JRadioButton intranetRadioButton;

	private JRadioButton internetRadioButton;

	private JRadioButton customRadioButton;

	private JButton stopButton;

	private static final String DEFAULT_PROPERTIES_FILE = "/common-build-gui.properties";

	private static final String DEFAULT_REPOSITORY_LOCATION_PROP = "commonbuild.repository.dir";

	private static final String MODULES_FILE_PROP = "commonbuild.modules.file";

	private static final String DEFAULT_PROPERTIES_FILE_PROP = "commonbuild.default-properties.file";

	private static final String BUILD_FILE_PROP = "commonbuild.build.file";

	private static final String USERS_DIR_PROP = "commonbuild.users.dir";

	private static final String ALL_TARGET = "all";

	private static final int OUTPUT_INDEX = 3;

	private static final String INTERNET_DEPLOYMENT_TARGET_DIR = "//hqlprfigis1.hq.un.fao.org/figis-deploy";

	private static final String INTRANET_DEPLOYMENT_TARGET_DIR = "//Hqldvfigis1.hq.un.fao.org/work/FIGIS/live";

	private static final String FI_DEPLOYMENT_TARGET_DIR = "//Hqldvfigis1.hq.un.fao.org/work/FIGIS/fi";

	private static final String VALID_DEPLOYMENT_TARGET_DIR = "//Hqldvfigis1.hq.un.fao.org/work/FIGIS/valid";

	private static final String VALIDDV_DEPLOYMENT_TARGET_DIR = "//Hqldvfigis1.hq.un.fao.org/work/FIGIS/validDV";

	private static final String DEVEL_DEPLOYMENT_TARGET_DIR = "//Hqldvfigis1.hq.un.fao.org/work/FIGIS/devel";

	private static final String DEPLOY_DEPLOYMENT_TARGET_DIR = "//Hqldvfigis1.hq.un.fao.org/work/FIGIS/deploy";

	private static final String DEPLOYMENT_TRACK_DIR = "/deployments";

	private static final String DEPLOYMENT_LOG_FILE = DEPLOYMENT_TRACK_DIR + "/deployments.log";

	private static final String DEPLOYMENT_FILE = DEPLOYMENT_TRACK_DIR + "/deployment-file.txt";

	private static final String DEPLOYMENT_FILE_TMP = DEPLOYMENT_TRACK_DIR + "/deployment-file.tmp";

	private static final String RELEASE_DIR = "//HQFILE1/figis/deployments";

	private JCheckBox buildFigisCheckBox;

	private JButton saveReleaseButton;

	private JPanel selectionPanel;

	private JButton removeButton;

	private JButton removeAllButton;

	private JButton addButton;

	private JList selectedList;

	private JList availableList;

	private JCheckBox defaultPropertiesCheckBox;

	private JTextField rcsCheckOutTextField;

	private JButton rcsDirButton;

	private JButton goButton;

	private JCheckBox productionCheckBox;

	private JCheckBox bundleCheckBox;

	private JCheckBox jarCheckBox;

	private JCheckBox deployCheckBox;

	private JCheckBox distCheckBox;

	private JCheckBox buildCheckBox;

	private JCheckBox rcsCheckBox;

	private JTextField deployTextField;

	private JTextField distTextField;

	private JTextField buildTextField;

	private JTextField srcTextField;

	private JButton newReleaseButton;

	private JButton loadReleaseButton;

	private JComboBox moduleNameComboBox;

	private JEditorPane propertiesEditorPane;

	private JTextArea outputTextArea;

	private File propertiesFile;

	private File modulesFile;

	private JProgressBar progressBar;

	private JButton buildDirButton;

	private JButton distDirButton;

	private JButton srcDirButton;

	private JButton deployDirButton;

	private JButton saveasButton;

	private JButton saveButton;

	private JButton loadButton;

	private JButton defaultButton;

	private JFileChooser chooser;

	private JTabbedPane tabbedPane;

	private JScrollPane outputScrollPane;

	private JComboBox moduleReleaseComboBox;

	private DefaultListModel availableListModel;

	private DefaultListModel selectedListModel;

	private Properties properties;

	private String userDir;

	private SwingWorker buildWorker;

	public static void main(String args[]) throws Exception {
		JFrame.setDefaultLookAndFeelDecorated(true);
		CommonBuildToolJava7 frame = new CommonBuildToolJava7();
		frame.setBounds(100, 100, 900, 587);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.show();
	}

	private Properties loadProperties() throws Exception {
		Properties props = new Properties();
		// props.load(this.getClass().getClassLoader().getResourceAsStream(DEFAULT_PROPERTIES_FILE));
		props.load(this.getClass().getResourceAsStream(DEFAULT_PROPERTIES_FILE));
		return props;
	}

	private void createUserDir() throws Exception {
		File user_dir = new File(userDir);
		user_dir.mkdirs();
	}

	private Collection loadModules() {
		Collection result = new ArrayList();
		try {
			BufferedReader in = new BufferedReader(new FileReader(modulesFile));
			String line = null;
			while ((line = in.readLine()) != null) {
				result.add(line);
			}
			in.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return result;
	}

	private Collection saveModules() {
		Collection result = new ArrayList();
		Enumeration elements = selectedListModel.elements();
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(modulesFile));
			String line = null;
			while (elements.hasMoreElements()) {
				out.write((String) elements.nextElement());
				out.newLine();
			}
			out.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return result;
	}

	private void loadSelection() {
		Collection loaded = loadModules();
		Iterator iterator = loaded.iterator();
		while (iterator.hasNext()) {
			boolean add_ok = true;
			String selected = (String) iterator.next();
			selected.trim();
			final String the_module_name = selected.substring(0, selected.indexOf(":"));
			final String the_module_release = selected.substring(selected.indexOf(":") + 1, selected.length());
			if (!availableListModel.contains(the_module_name)) {
				JOptionPane.showMessageDialog(CommonBuildToolJava7.this,
						"The module " + the_module_name + " is not defined!", "Warning", JOptionPane.WARNING_MESSAGE);
				add_ok = false;
			} else {
				Collection module_releases = getModuleReleases(the_module_name);
				if (!module_releases.contains(the_module_release)) {
					/*
					 * JOptionPane.showMessageDialog( CommonBuildFrame.this,
					 * "The release " + the_module_release +
					 * " is not defined for " + the_module_name +
					 * " is not defined!", "Warning",
					 * JOptionPane.WARNING_MESSAGE);
					 */
					int choice = JOptionPane.showConfirmDialog(
							CommonBuildToolJava7.this, "The release " + the_module_release + " is not defined for "
									+ the_module_name + ", do you want to define it?",
							"Warning", JOptionPane.OK_CANCEL_OPTION);
					if (choice == JOptionPane.OK_OPTION) {
						if (!the_module_release.matches("[0-9]+_[0-9]+_[0-9]+")) {
							JOptionPane.showMessageDialog(CommonBuildToolJava7.this, "Invalid release symbolic name!",
									"Error", JOptionPane.ERROR_MESSAGE);
							add_ok = false;
						} else {
							try {
								addRelease(the_module_name, the_module_release);
								choice = JOptionPane.showConfirmDialog(CommonBuildToolJava7.this,
										"Do you want to mark the sources in RCS?", "Mark RCS",
										JOptionPane.YES_NO_OPTION);
								if (choice == JOptionPane.YES_OPTION) {
									SwingWorker worker = new SwingWorker() {

										public Object construct() {
											try {
												csrcsMark(the_module_name, the_module_release);
											} catch (Exception ex) {
												JOptionPane.showMessageDialog(CommonBuildToolJava7.this,
														"Exception caught:\n" + ex.getMessage(), "Error",
														JOptionPane.ERROR_MESSAGE);
											}
											return "OK";
										}
									};
									worker.start();
								}
							} catch (IllegalArgumentException iae) {
								JOptionPane.showMessageDialog(CommonBuildToolJava7.this,
										"Release symbolic name already defined!", "Warning",
										JOptionPane.WARNING_MESSAGE);
							} catch (Exception ex) {
								JOptionPane.showMessageDialog(CommonBuildToolJava7.this,
										"Exception caught: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
							}
						}
					} else {
						add_ok = false;
					}
				}
			}
			if (add_ok) {
				availableListModel.removeElement(the_module_name);
				selectedListModel.addElement(selected);
				availableList.validate();
				selectedList.validate();
			}
		}
	}

	private void loadModulesFile() {
		chooser.setDialogTitle("Select the release descriptor to open...");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setCurrentDirectory(new File(RELEASE_DIR));
		int return_val = chooser.showOpenDialog(CommonBuildToolJava7.this);
		if (return_val == JFileChooser.APPROVE_OPTION) {
			modulesFile = chooser.getSelectedFile();
			resetAvailableListModel();
			resetSelectedListModel();
			loadSelection();
		}
	}

	private void saveModulesFile() {
		chooser.setDialogTitle("Select the release descriptor to save...");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		if (modulesFile != null) {
			chooser.setSelectedFile(modulesFile);
		}
		int return_val = chooser.showSaveDialog(CommonBuildToolJava7.this);
		if (return_val == JFileChooser.APPROVE_OPTION) {
			modulesFile = chooser.getSelectedFile();
			saveModules();
		}
	}

	private void loadSourceTextField() {
		chooser.setDialogTitle("Select the sources home directory...");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int return_val = chooser.showOpenDialog(CommonBuildToolJava7.this);
		if (return_val == JFileChooser.APPROVE_OPTION) {
			try {
				srcTextField.setText(chooser.getSelectedFile().getCanonicalPath());
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}

	private void loadBuildTextField() {
		chooser.setDialogTitle("Select the build home directory...");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int return_val = chooser.showOpenDialog(CommonBuildToolJava7.this);
		if (return_val == JFileChooser.APPROVE_OPTION) {
			try {
				buildTextField.setText(chooser.getSelectedFile().getCanonicalPath());
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}

	private void loadDistributeTextField() {
		chooser.setDialogTitle("Select the distribution home directory...");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int return_val = chooser.showOpenDialog(CommonBuildToolJava7.this);
		if (return_val == JFileChooser.APPROVE_OPTION) {
			try {
				distTextField.setText(chooser.getSelectedFile().getCanonicalPath());
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}

	private void loadDeployTextField() {
		chooser.setDialogTitle("Select the deployment home directory...");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int return_val = chooser.showOpenDialog(CommonBuildToolJava7.this);
		if (return_val == JFileChooser.APPROVE_OPTION) {
			try {
				deployTextField.setText(chooser.getSelectedFile().getCanonicalPath());
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}

	private void loadRcsCheckOutTextField() {
		chooser.setDialogTitle("Select the RCS check-out home directory...");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int return_val = chooser.showOpenDialog(CommonBuildToolJava7.this);
		if (return_val == JFileChooser.APPROVE_OPTION) {
			try {
				rcsCheckOutTextField.setText(chooser.getSelectedFile().getCanonicalPath());
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}

	private String getDeploymentTargetDir() {
		if (internetRadioButton.isSelected()) {
			return INTERNET_DEPLOYMENT_TARGET_DIR;
		} else if (intranetRadioButton.isSelected()) {
			return INTRANET_DEPLOYMENT_TARGET_DIR;
		} else if (validRadioButton.isSelected()) {
			return VALID_DEPLOYMENT_TARGET_DIR;
		} else if (fiRadioButton.isSelected()) {
			return FI_DEPLOYMENT_TARGET_DIR;
		} else if (develRadioButton.isSelected()) {
			return DEVEL_DEPLOYMENT_TARGET_DIR;
		} else if (deployRadioButton.isSelected()) {
			return DEPLOY_DEPLOYMENT_TARGET_DIR;
		} else if (validDvRadioButton.isSelected()) {
			return VALIDDV_DEPLOYMENT_TARGET_DIR;
		} else if (customRadioButton.isSelected()) {
			return deployTextField.getText();
		}
		return null;
	}

	private void trackDeployment(String deploymentTargetDir, String moduleName, String moduleRelease) {
		StringBuffer line = new StringBuffer();
		line.append(moduleName).append(':').append(moduleRelease).append(", on ")
				.append(new Date(System.currentTimeMillis())).append(" by ")
				.append(System.getProperties().getProperty("user.name"));
		StringBuffer deployment_line = new StringBuffer();
		deployment_line.append(moduleName).append(':').append(moduleRelease);
		try {
			// add line to the log file
			File dir = new File(deploymentTargetDir + DEPLOYMENT_TRACK_DIR);
			if (!dir.exists()) {
				dir.mkdir();
			}
			File log_file = new File(deploymentTargetDir + DEPLOYMENT_LOG_FILE);
			boolean exists = log_file.exists();
			if (!exists) {
				log_file.createNewFile();
			}
			BufferedWriter log_writer = null;
			if (exists) {
				log_writer = new BufferedWriter(new FileWriter(log_file, true));
			} else {
				log_writer = new BufferedWriter(new FileWriter(log_file, false));
			}
			log_writer.write(line.toString());
			log_writer.newLine();
			log_writer.close();
			// update line in the deployment file
			File deployment_file = new File(deploymentTargetDir + DEPLOYMENT_FILE);
			File deployment_file_tmp = new File(deploymentTargetDir + DEPLOYMENT_FILE_TMP);
			exists = deployment_file.exists();
			if (!exists) {
				deployment_file.createNewFile();
			} else {
				deployment_file.renameTo(deployment_file_tmp);
				deployment_file = new File(deploymentTargetDir + DEPLOYMENT_FILE);
			}
			deployment_file_tmp.createNewFile();
			BufferedReader deployment_reader = new BufferedReader(new FileReader(deployment_file_tmp));
			BufferedWriter deployment_writer = new BufferedWriter(new FileWriter(deployment_file));
			String reader_line = null;
			boolean found = false;
			while ((reader_line = deployment_reader.readLine()) != null) {
				StringTokenizer tokenizer = new StringTokenizer(reader_line, ":");
				if (tokenizer.nextToken().equals(moduleName)) {
					deployment_writer.write(moduleName + ":" + moduleRelease + "\n");
					found = true;
				} else {
					deployment_writer.write(reader_line + "\n");
				}
			}
			if (!found) {
				deployment_writer.write(moduleName + ":" + moduleRelease + "\n");
			}
			deployment_writer.flush();
			deployment_writer.close();
			deployment_reader.close();
			deployment_file_tmp.delete();
		} catch (IOException ioe) {
			outputTextArea
					.append("WARNING: Deployment Tracking File update failed because of " + ioe.getMessage() + "\n");
			ioe.printStackTrace();
		}
	}

	private void build(String moduleName, String moduleRelease, String userPropertiesFilename) throws Exception {
		// String deployment_target_dir = deployTextField.getText();
		String deployment_target_dir = getDeploymentTargetDir();
		String src_dir = srcTextField.getText();
		String rcs_dir = rcsCheckOutTextField.getText();
		String dist_dir = distTextField.getText();
		String build_dir = buildTextField.getText();
		boolean building_figis = buildFigisCheckBox.isSelected();
		if (deployCheckBox.isSelected()) {
			boolean exists = (new File(deployment_target_dir)).exists();
			if (!exists) {
				outputTextArea
						.append("Target deployment home directory does not exist: " + deployment_target_dir + "\n");
				return;
			}
		}
		Project project = new Project();
		project.setProperty("build.module.name", moduleName);
		project.setProperty("build.module.release", moduleRelease);
		if (!defaultPropertiesCheckBox.isSelected()) {
			if (rcsCheckBox.isSelected()) {
				project.setProperty("build.rcscheckout.enabled", "true");
				if (!building_figis) {
					project.setProperty("build.rcscheckout.dir", rcs_dir);
					project.setProperty("build.src.dir", rcs_dir + "/src");
				}
			} else {
				project.setProperty("build.rcscheckout.enabled", "false");
				if (!building_figis) {
					project.setProperty("build.src.dir", src_dir);
				}
			}
			if (buildCheckBox.isSelected()) {
				if (!building_figis) {
					project.setProperty("build.build.dir", build_dir);
				}
				project.setProperty("build.build.enabled", "true");
				project.setProperty("build.bundle.enabled", bundleCheckBox.isSelected() ? "true" : "false");
				project.setProperty("build.jars.enabled", jarCheckBox.isSelected() ? "true" : "false");

				project.setProperty("build.api.enabled", "false");
				project.setProperty("build.java2html.enabled", "false");
				project.setProperty("build.checkstyle.enabled", "false");
				project.setProperty("build.jdepend.enabled", "false");
				project.setProperty("build.junit.enabled", "false");

			} else {
				project.setProperty("build.build.enabled", "false");
			}
			if (distCheckBox.isSelected()) {
				project.setProperty("build.dist.enabled", "true");
				if (!building_figis) {
					project.setProperty("build.dist.dir", dist_dir);
				}
				project.setProperty("build.dist.production.enabled",
						productionCheckBox.isSelected() ? "true" : "false");
			} else {
				project.setProperty("build.dist.enabled", "false");
			}
			if (deployCheckBox.isSelected()) {
				project.setProperty("build.deploy.enabled", "true");
				project.setProperty("build.deploy.target", deployment_target_dir);
			} else {
				project.setProperty("build.deploy.enabled", "false");
			}
		}
		project.setProperty("user.xmlproperties.file", userPropertiesFilename);
		project.setProperty("ant.file.build", properties.getProperty(BUILD_FILE_PROP));
		project.init();
		PrintStream ps = new PrintStream(
				new FileOutputStream(userDir + "/" + moduleName + "-" + moduleRelease + "-build.log"));
		BuildLogger logger = new DefaultLogger();
		logger.setMessageOutputLevel(Project.MSG_INFO);
		logger.setOutputPrintStream(ps);
		logger.setErrorPrintStream(ps);
		System.setOut(ps);
		System.setErr(ps);
		logger.setEmacsMode(true);
		project.addBuildListener(logger);
		BuildListener listener = new BuildListener() {

			public void buildStarted(BuildEvent arg0) {
			}

			public void buildFinished(BuildEvent arg0) {
			}

			public void targetStarted(BuildEvent arg0) {
			}

			public void targetFinished(BuildEvent arg0) {
			}

			public void taskStarted(BuildEvent arg0) {
			}

			public void taskFinished(BuildEvent arg0) {
			}

			public void messageLogged(BuildEvent event) {
				if (event.getPriority() <= Project.MSG_INFO) {
					outputTextArea.append(event.getMessage() + "\n");
					EventQueue.invokeLater(new Runnable() {

						public void run() {
							outputScrollPane.getViewport().setViewPosition(new Point(0, outputTextArea.getHeight()));
						}
					});
				}
			}
		};
		ProjectHelper.getProjectHelper().parse(project, new File(properties.getProperty(BUILD_FILE_PROP)));
		project.addBuildListener(listener);
		progressBar.setIndeterminate(true);
		progressBar.setString("Building...");
		try {
			project.executeTarget(ALL_TARGET);
			if (deployCheckBox.isSelected()) {
				trackDeployment(deployment_target_dir, moduleName, moduleRelease);
			}
		} catch (BuildException be) {
			outputTextArea.append(be.getMessage() + "\n");
		}
		outputTextArea.append("Done.\n");
		ps.flush();
		ps.close();
	}

	private void build() {
		outputTextArea.setText("");
		try {
			goButton.setEnabled(false);
			progressBar.setString(null);
			File tmp_properties = new File(userDir + "/tmp-properties.xml");
			BufferedWriter out = new BufferedWriter(new FileWriter(tmp_properties));
			out.write(propertiesEditorPane.getText());
			out.flush();
			out.close();
			if (buildFigisCheckBox.isSelected()) {
				// Whole FIGIS application
				if ((selectedListModel == null) || (selectedListModel.size() == 0)) {
					outputTextArea.append("No module selected!\n");
				} else {
					List modules = Arrays.asList(selectedListModel.toArray());
					outputTextArea.append(modules.toString() + "\n");
					Iterator modules_iterator = modules.iterator();
					while (modules_iterator.hasNext()) {
						String the_module = (String) modules_iterator.next();
						String the_module_name = the_module.substring(0, the_module.indexOf(":"));
						String the_module_release = the_module.substring(the_module.indexOf(":") + 1,
								the_module.length());
						outputTextArea.append("building: " + the_module_name + ":" + the_module_release + "\n");
						build(the_module_name, the_module_release, tmp_properties.getAbsolutePath());
					}
				}
			} else {
				// Individual module
				String module_name = moduleNameComboBox.getSelectedItem().toString();
				String module_release = moduleReleaseComboBox.getSelectedItem().toString();
				build(module_name, module_release, tmp_properties.getAbsolutePath());
			}
			progressBar.setIndeterminate(false);
			progressBar.setValue(100);
			progressBar.setString(null);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			progressBar.setString("");
			progressBar.setValue(0);
			goButton.setEnabled(true);
		}
	}

	private Vector getModuleReleases(String module) {
		Vector result = new Vector();
		try {
			String file_name = properties.getProperty(DEFAULT_REPOSITORY_LOCATION_PROP) + "/modules/" + module
					+ "/releases.txt";
			BufferedReader in = new BufferedReader(new FileReader(file_name));
			String release = null;
			while ((release = in.readLine()) != null) {
				result.add(release);
			}
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return result;
	}

	private Vector getModules() {
		Vector result = new Vector();
		try {
			BufferedReader in = new BufferedReader(new FileReader(properties.getProperty(MODULES_FILE_PROP)));
			String module = null;
			while ((module = in.readLine()) != null) {
				result.add(module);
			}
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return result;
	}

	private void setModuleReleaseComboBoxModel() {
		moduleReleaseComboBox
				.setModel(new DefaultComboBoxModel(getModuleReleases((String) moduleNameComboBox.getSelectedItem())));
	}

	private void setModuleNameComboBoxModel() {
		moduleNameComboBox.setModel(new DefaultComboBoxModel(getModules()));
	}

	private void resetAvailableListModel() {
		if (availableListModel == null) {
			availableListModel = new DefaultListModel();
		} else {
			availableListModel.clear();
		}
		Iterator iterator = getModules().iterator();
		while (iterator.hasNext()) {
			availableListModel.addElement(iterator.next());
		}
	}

	private void resetSelectedListModel() {
		if (selectedListModel == null) {
			selectedListModel = new DefaultListModel();
		} else {
			selectedListModel.clear();
		}
	}

	private void loadPropertiesEditorPane() {
		try {
			BufferedReader in = new BufferedReader(new FileReader(propertiesFile));
			StringBuffer text_buffer = new StringBuffer();
			String line = null;
			while ((line = in.readLine()) != null) {
				text_buffer.append(line);
				text_buffer.append("\n");
			}
			propertiesEditorPane.setText(text_buffer.toString());
			in.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	private void saveBuildProperties() {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(propertiesFile));
			out.write(propertiesEditorPane.getText());
			out.flush();
			out.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	private void saveAsBuildProperties() {
		int return_val = chooser.showSaveDialog(CommonBuildToolJava7.this);
		if (return_val == JFileChooser.APPROVE_OPTION) {
			propertiesFile = chooser.getSelectedFile();
			saveBuildProperties();
			saveButton.setEnabled(true);
		}
	}

	private void loadBuildProperties() {
		chooser.setDialogTitle("Select the build properties file...");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int return_val = chooser.showOpenDialog(CommonBuildToolJava7.this);
		if (return_val == JFileChooser.APPROVE_OPTION) {
			propertiesFile = chooser.getSelectedFile();
			loadPropertiesEditorPane();
			saveButton.setEnabled(true);
		}
	}

	private void addRelease(String module, String release) throws Exception {
		String releases_file = properties.getProperty(DEFAULT_REPOSITORY_LOCATION_PROP) + "/modules/" + module
				+ "/releases.txt";
		BufferedReader in = new BufferedReader(new FileReader(new File(releases_file)));
		StringBuffer text_buffer = new StringBuffer();
		String line = null;
		while ((line = in.readLine()) != null) {
			if (line.equals(release)) {
				throw (new IllegalArgumentException("Release: " + release + " already defined for module: " + module));
			}
		}
		in.close();
		RandomAccessFile raf = new RandomAccessFile(releases_file, "rw");
		long length = raf.length();
		raf.seek(length);
		if (length != 0) {
			raf.writeBytes("\n");
		}
		raf.writeBytes(release);
		raf.close();
		setModuleReleaseComboBoxModel();
	}

	private void selectedModuleChanged() {
		String module_name = moduleNameComboBox.getSelectedItem().toString();
		String module_release = moduleReleaseComboBox.getSelectedItem().toString();
		srcTextField.setText(properties.getProperty(DEFAULT_REPOSITORY_LOCATION_PROP) + "/modules/" + module_name + "/"
				+ module_release + "/src");
		buildTextField.setText(properties.getProperty(DEFAULT_REPOSITORY_LOCATION_PROP) + "/modules/" + module_name
				+ "/" + module_release + "/build");
		distTextField.setText(properties.getProperty(DEFAULT_REPOSITORY_LOCATION_PROP) + "/modules/" + module_name + "/"
				+ module_release + "/dist");
		rcsCheckOutTextField.setText(properties.getProperty(DEFAULT_REPOSITORY_LOCATION_PROP) + "/modules/"
				+ module_name + "/" + module_release);
		srcDirButton.setEnabled(false);
		buildDirButton.setEnabled(true);
		distDirButton.setEnabled(true);
		rcsDirButton.setEnabled(true);
		srcTextField.setEnabled(false);
		buildTextField.setEnabled(true);
		distTextField.setEnabled(true);
		rcsCheckOutTextField.setEnabled(true);
		setModuleReleaseComboBoxModel();
	}

	private void init() throws Exception {
		properties = loadProperties();
		propertiesFile = new File(DEFAULT_PROPERTIES_FILE);
		userDir = properties.getProperty(USERS_DIR_PROP) + "/" + System.getProperties().getProperty("user.name");
		createUserDir();
	}

	private void csrcsMark(String module, String release) throws Exception {
		// call CSRCS Mark /pmodule /nrelease /q
		String command = "CSRCS Mark /p\"" + module + "\" /n" + release + " /q";
		Runtime.getRuntime().exec(command);
	}

	public CommonBuildToolJava7() throws Exception {
		super();
		init();
		getContentPane().setLayout(null);
		{
			final JLabel label = new JLabel();
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setAlignmentX(Component.CENTER_ALIGNMENT);
			label.setFont(new Font("Arial", Font.BOLD, 18));
			label.setBounds(175, 20, 375, 35);
			getContentPane().add(label);
			label.setText("Figis Common Build Tool");
		}
		{
			tabbedPane = new JTabbedPane();
			tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
			tabbedPane.setAutoscrolls(true);
			tabbedPane.setBounds(15, 70, 850, 400);
			getContentPane().add(tabbedPane);
			{
				final JPanel panel = new JPanel();
				panel.setLayout(null);
				tabbedPane.addTab("Module", null, panel, null);
				{
					final JLabel label = new JLabel();
					label.setBounds(10, 35, 90, 20);
					label.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
					panel.add(label);
					label.setText("Name");
				}
				{
					moduleNameComboBox = new JComboBox();
					moduleNameComboBox.setBounds(105, 35, 160, 25);
					moduleNameComboBox.setEditable(false);
					moduleNameComboBox.addItemListener(new ItemListener() {

						public void itemStateChanged(ItemEvent e) {
							selectedModuleChanged();
						}
					});
					setModuleNameComboBoxModel();
					moduleNameComboBox.setEnabled(true);
					panel.add(moduleNameComboBox);
				}
				{
					final JLabel label = new JLabel();
					label.setBounds(290, 35, 65, 20);
					label.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
					label.setAlignmentX(Component.CENTER_ALIGNMENT);
					panel.add(label);
					label.setText("Release");
				}
				{
					moduleReleaseComboBox = new JComboBox();
					moduleReleaseComboBox.setBounds(360, 35, 160, 25);
					moduleReleaseComboBox.addItemListener(new ItemListener() {

						public void itemStateChanged(ItemEvent e) {
							String module_release = (String) moduleReleaseComboBox.getSelectedItem();
							String module_name = moduleNameComboBox.getSelectedItem().toString();
							srcTextField.setText(properties.getProperty(DEFAULT_REPOSITORY_LOCATION_PROP) + "/modules/"
									+ module_name + "/" + moduleReleaseComboBox.getSelectedItem() + "/src");
							buildTextField
									.setText(properties.getProperty(DEFAULT_REPOSITORY_LOCATION_PROP) + "/modules/"
											+ module_name + "/" + moduleReleaseComboBox.getSelectedItem() + "/build");
							distTextField.setText(properties.getProperty(DEFAULT_REPOSITORY_LOCATION_PROP) + "/modules/"
									+ module_name + "/" + moduleReleaseComboBox.getSelectedItem() + "/dist");
							rcsCheckOutTextField.setText(properties.getProperty(DEFAULT_REPOSITORY_LOCATION_PROP)
									+ "/modules/" + module_name + "/" + moduleReleaseComboBox.getSelectedItem());
						}
					});
					setModuleReleaseComboBoxModel();
					moduleReleaseComboBox.setEnabled(true);
					panel.add(moduleReleaseComboBox);
				}
				newReleaseButton = new JButton();
				newReleaseButton.setBounds(525, 35, 60, 25);
				newReleaseButton.setToolTipText("Define a new module release");
				newReleaseButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						final String new_release = JOptionPane.showInputDialog(CommonBuildToolJava7.this,
								"New release symbolic name:", "New release", JOptionPane.QUESTION_MESSAGE);
						if (new_release != null) {
							if (!new_release.matches("[0-9]+_[0-9]+_[0-9]+")) {
								JOptionPane.showMessageDialog(CommonBuildToolJava7.this,
										"Invalid release symbolic name!", "Error", JOptionPane.ERROR_MESSAGE);
							} else {
								final String module = (String) moduleNameComboBox.getSelectedItem();
								try {
									addRelease(module, new_release);
									int choice = JOptionPane.showConfirmDialog(CommonBuildToolJava7.this,
											"Do you want to mark the sources in RCS?", "Mark RCS",
											JOptionPane.YES_NO_OPTION);
									if (choice == JOptionPane.YES_OPTION) {
										SwingWorker worker = new SwingWorker() {

											public Object construct() {
												try {
													csrcsMark(module, new_release);
												} catch (Exception ex) {
													JOptionPane.showMessageDialog(CommonBuildToolJava7.this,
															"Exception caught:\n" + ex.getMessage(), "Error",
															JOptionPane.ERROR_MESSAGE);
												}
												return "OK";
											}
										};
										worker.start();
									}
								} catch (IllegalArgumentException iae) {
									JOptionPane.showMessageDialog(CommonBuildToolJava7.this,
											"Release symbolic name already defined!", "Warning",
											JOptionPane.WARNING_MESSAGE);
								} catch (Exception ex) {
									JOptionPane.showMessageDialog(CommonBuildToolJava7.this,
											"Exception caught: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
								}
							}
						}
					}
				});
				panel.add(newReleaseButton);
				newReleaseButton.setText("New");
				newReleaseButton.setEnabled(true);
				defaultPropertiesCheckBox = new JCheckBox();
				defaultPropertiesCheckBox.setBounds(325, 75, 180, 25);
				defaultPropertiesCheckBox.addItemListener(new ItemListener() {

					public void itemStateChanged(ItemEvent e) {
						if (e.getStateChange() == ItemEvent.SELECTED) {
							tabbedPane.setEnabledAt(1, false);
						} else {
							tabbedPane.setEnabledAt(1, true);
						}
					}
				});
				panel.add(defaultPropertiesCheckBox);
				defaultPropertiesCheckBox.setText("Use default properties");
				selectionPanel = new JPanel();
				selectionPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
				selectionPanel.setLayout(null);
				selectionPanel.setBounds(65, 110, 720, 190);
				panel.add(selectionPanel);
				resetAvailableListModel();
				availableList = new JList(availableListModel);
				final JScrollPane available_pane = new JScrollPane();
				available_pane.setBounds(20, 30, 215, 145);
				available_pane.setViewportView(availableList);
				selectionPanel.add(available_pane);
				resetSelectedListModel();
				selectedList = new JList(selectedListModel);
				final JScrollPane selected_pane = new JScrollPane();
				selected_pane.setBounds(355, 30, 220, 145);
				selected_pane.setViewportView(selectedList);
				selectionPanel.add(selected_pane);
				addButton = new JButton();
				addButton.setBounds(247, 55, 100, 25);
				addButton.setToolTipText("Add the selected module");
				addButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						if (!availableList.isSelectionEmpty()) {
							String module = (String) availableList.getSelectedValue();
							Vector releases_vector = getModuleReleases(module);
							String[] releases = new String[releases_vector.size()];
							releases = (String[]) getModuleReleases(module).toArray(releases);
							String release = (String) JOptionPane.showInputDialog(CommonBuildToolJava7.this,
									"Slect a release:", module, JOptionPane.QUESTION_MESSAGE, null, releases,
									releases[releases.length - 1]);
							if (release != null) {
								availableListModel.removeElement(module);
								selectedListModel.addElement(module + ":" + release);
							}
						}
					}
				});
				selectionPanel.add(addButton);
				addButton.setText("Add");
				removeButton = new JButton();
				removeButton.setBounds(247, 95, 100, 25);
				removeButton.setToolTipText("Remove the selected module");
				removeButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						if (!selectedList.isSelectionEmpty()) {
							String selected = (String) selectedList.getSelectedValue();
							String the_module_name = selected.substring(0, selected.indexOf(":"));
							availableListModel.addElement(the_module_name);
							Object[] availables = availableListModel.toArray();
							Arrays.sort(availables);
							availableListModel.clear();
							for (int i = 0; i < availables.length; availableListModel.addElement(availables[i++]))
								;
							selectedListModel.removeElement(selected);
						}
					}
				});
				selectionPanel.add(removeButton);
				removeButton.setText("Remove");
				removeAllButton = new JButton();
				removeAllButton.setBounds(247, 135, 100, 25);
				removeAllButton.setToolTipText("Remove all the selected modules");
				removeAllButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						if (!selectedListModel.isEmpty()) {
							List modules = Arrays.asList(selectedListModel.toArray());
							Iterator iterator = modules.iterator();
							while (iterator.hasNext()) {
								String selected = (String) iterator.next();
								String the_module_name = selected.substring(0, selected.indexOf(":"));
								availableListModel.addElement(the_module_name);
							}
							Object[] availables = availableListModel.toArray();
							Arrays.sort(availables);
							availableListModel.clear();
							for (int i = 0; i < availables.length; availableListModel.addElement(availables[i++]))
								;
							selectedListModel.removeAllElements();
						}
					}
				});
				selectionPanel.add(removeAllButton);
				removeAllButton.setText("Remove all");
				final JLabel availableModulesLabel = new JLabel();
				availableModulesLabel.setBounds(25, 0, 150, 25);
				selectionPanel.add(availableModulesLabel);
				availableModulesLabel.setText("Available modules");
				loadReleaseButton = new JButton();
				loadReleaseButton.setBounds(595, 55, 100, 25);
				loadReleaseButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						loadModulesFile();
					}
				});
				selectionPanel.add(loadReleaseButton);
				loadReleaseButton.setText("Load...");
				saveReleaseButton = new JButton();
				saveReleaseButton.setBounds(595, 95, 100, 25);
				saveReleaseButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						saveModulesFile();
					}
				});
				selectionPanel.add(saveReleaseButton);
				saveReleaseButton.setText("Save...");
				final JLabel selectedModulesLabel = new JLabel();
				selectedModulesLabel.setBounds(355, 0, 170, 25);
				selectionPanel.add(selectedModulesLabel);
				selectedModulesLabel.setText("Selected modules:releases");
				buildFigisCheckBox = new JCheckBox();
				buildFigisCheckBox.setBounds(75, 75, 180, 25);
				buildFigisCheckBox.setSelected(false);
				buildFigisCheckBox.addItemListener(new ItemListener() {

					public void itemStateChanged(ItemEvent e) {
						if (e.getStateChange() == ItemEvent.SELECTED) {
							moduleNameComboBox.setEnabled(false);
							moduleReleaseComboBox.setEnabled(false);
							newReleaseButton.setEnabled(false);
							selectionPanel.setVisible(true);
							srcDirButton.setEnabled(false);
							buildDirButton.setEnabled(false);
							distDirButton.setEnabled(false);
							rcsDirButton.setEnabled(false);
							srcTextField.setEnabled(false);
							rcsCheckOutTextField.setEnabled(false);
							srcTextField.setText(null);
							buildTextField.setText(null);
							distTextField.setText(null);
							rcsCheckOutTextField.setText(null);
						} else {
							moduleNameComboBox.setEnabled(true);
							moduleReleaseComboBox.setEnabled(true);
							newReleaseButton.setEnabled(true);
							selectionPanel.setVisible(false);
							selectedModuleChanged();
						}
					}
				});
				panel.add(buildFigisCheckBox);
				buildFigisCheckBox.setText("Build FIGIS (sub)system");
				selectionPanel.setVisible(false);
			}
			{
				final JPanel panel = new JPanel();
				panel.setLayout(null);
				tabbedPane.addTab("Properties", null, panel, null);
				final JLabel label = new JLabel();
				label.setBounds(30, 20, 100, 25);
				panel.add(label);
				label.setText("Sources");
				final JLabel label_1 = new JLabel();
				label_1.setBounds(235, 20, 100, 25);
				panel.add(label_1);
				label_1.setText("Build");
				final JLabel label_2 = new JLabel();
				label_2.setBounds(440, 20, 100, 25);
				panel.add(label_2);
				label_2.setText("Distribute");
				final JLabel label_3 = new JLabel();
				label_3.setBounds(645, 20, 100, 25);
				panel.add(label_3);
				label_3.setText("Deploy");
				srcTextField = new JTextField();
				srcTextField.setBounds(55, 75, 165, 20);
				panel.add(srcTextField);
				buildTextField = new JTextField();
				buildTextField.setBounds(255, 75, 170, 20);
				panel.add(buildTextField);
				distTextField = new JTextField();
				distTextField.setBounds(460, 75, 170, 20);
				panel.add(distTextField);
				deployTextField = new JTextField();
				deployTextField.setBounds(665, 75, 170, 20);
				deployTextField.setEnabled(false);
				panel.add(deployTextField);
				srcDirButton = new JButton();
				srcDirButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						loadSourceTextField();
					}
				});
				srcDirButton.setBounds(30, 75, 25, 20);
				panel.add(srcDirButton);
				srcDirButton.setText("...");
				buildDirButton = new JButton();
				buildDirButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						loadBuildTextField();
					}
				});
				buildDirButton.setBounds(230, 75, 25, 20);
				panel.add(buildDirButton);
				buildDirButton.setText("...");
				distDirButton = new JButton();
				distDirButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						loadDistributeTextField();
					}
				});
				distDirButton.setBounds(435, 75, 25, 20);
				panel.add(distDirButton);
				distDirButton.setText("...");
				deployDirButton = new JButton();
				deployDirButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						loadDeployTextField();
					}
				});
				deployDirButton.setBounds(640, 75, 25, 20);
				deployDirButton.setEnabled(false);
				panel.add(deployDirButton);
				deployDirButton.setText("...");
				rcsDirButton = new JButton();
				rcsDirButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						loadRcsCheckOutTextField();
					}
				});
				rcsDirButton.setBounds(30, 160, 25, 20);
				panel.add(rcsDirButton);
				rcsDirButton.setText("...");
				rcsCheckOutTextField = new JTextField();
				rcsCheckOutTextField.setBounds(55, 160, 165, 20);
				panel.add(rcsCheckOutTextField);
				rcsCheckBox = new JCheckBox();
				rcsCheckBox.addItemListener(new ItemListener() {

					public void itemStateChanged(ItemEvent e) {
						if (e.getStateChange() == ItemEvent.SELECTED) {
							if (!buildFigisCheckBox.isSelected()) {
								rcsDirButton.setEnabled(true);
								rcsCheckOutTextField.setEnabled(true);
							} else {
								rcsDirButton.setEnabled(false);
								rcsCheckOutTextField.setEnabled(false);
							}
							srcDirButton.setEnabled(false);
							srcTextField.setEnabled(false);
						} else {
							rcsDirButton.setEnabled(false);
							rcsCheckOutTextField.setEnabled(false);
							if (!buildFigisCheckBox.isSelected()) {
								srcDirButton.setEnabled(true);
								srcTextField.setEnabled(true);
							} else {
								srcDirButton.setEnabled(false);
								srcTextField.setEnabled(false);
							}
						}
					}
				});
				rcsCheckBox.setBounds(30, 125, 170, 25);
				rcsCheckBox.setSelected(true);
				panel.add(rcsCheckBox);
				rcsCheckBox.setText("RCS-checkout");
				bundleCheckBox = new JCheckBox();
				bundleCheckBox.setBounds(255, 155, 170, 25);
				bundleCheckBox.setSelected(true);
				panel.add(bundleCheckBox);
				bundleCheckBox.setText("Resource Bundle");
				jarCheckBox = new JCheckBox();
				jarCheckBox.setBounds(255, 175, 170, 25);
				jarCheckBox.setSelected(true);
				panel.add(jarCheckBox);
				jarCheckBox.setText("Jar");
				buildCheckBox = new JCheckBox();
				buildCheckBox.addItemListener(new ItemListener() {

					public void itemStateChanged(ItemEvent e) {
						if (e.getStateChange() == ItemEvent.SELECTED) {
							bundleCheckBox.setEnabled(true);
							jarCheckBox.setEnabled(true);
						} else {
							bundleCheckBox.setEnabled(false);
							jarCheckBox.setEnabled(false);
						}
					}
				});
				buildCheckBox.setBounds(235, 125, 170, 25);
				buildCheckBox.setSelected(true);
				panel.add(buildCheckBox);
				buildCheckBox.setText("Build");
				productionCheckBox = new JCheckBox();
				productionCheckBox.setBounds(460, 155, 170, 25);
				productionCheckBox.setSelected(false);
				panel.add(productionCheckBox);
				productionCheckBox.setText("Make Production");
				distCheckBox = new JCheckBox();
				distCheckBox.addItemListener(new ItemListener() {

					public void itemStateChanged(ItemEvent e) {
						if (e.getStateChange() == ItemEvent.SELECTED) {
							productionCheckBox.setEnabled(true);
						} else {
							productionCheckBox.setEnabled(false);
						}
					}
				});
				distCheckBox.setBounds(440, 125, 170, 25);
				distCheckBox.setSelected(true);
				panel.add(distCheckBox);
				distCheckBox.setText("Distribute");
				deployCheckBox = new JCheckBox();
				deployCheckBox.addItemListener(new ItemListener() {

					public void itemStateChanged(ItemEvent e) {
						Enumeration buttons = buttonGroup.getElements();
						if (e.getStateChange() == ItemEvent.SELECTED) {
							while (buttons.hasMoreElements()) {
								JRadioButton button = (JRadioButton) buttons.nextElement();
								button.setEnabled(true);
							}
							if (customRadioButton.isSelected()) {
								deployDirButton.setEnabled(true);
								deployTextField.setEnabled(true);
							}
						} else {
							while (buttons.hasMoreElements()) {
								JRadioButton button = (JRadioButton) buttons.nextElement();
								button.setEnabled(false);
							}
							deployDirButton.setEnabled(false);
							deployTextField.setEnabled(false);
						}
					}
				});
				deployCheckBox.setBounds(650, 125, 170, 25);
				deployCheckBox.setSelected(false);
				panel.add(deployCheckBox);
				deployCheckBox.setText("Deploy");
				internetRadioButton = new JRadioButton();
				internetRadioButton.setFont(new Font("", Font.BOLD, 12));
				internetRadioButton.setForeground(Color.RED);
				internetRadioButton.setEnabled(false);
				buttonGroup.add(internetRadioButton);
				internetRadioButton.setBounds(670, 150, 100, 25);
				panel.add(internetRadioButton);
				internetRadioButton.setText("Internet");
				intranetRadioButton = new JRadioButton();
				intranetRadioButton.setFont(new Font("", Font.BOLD, 12));
				intranetRadioButton.setForeground(Color.YELLOW);
				intranetRadioButton.setEnabled(false);
				buttonGroup.add(intranetRadioButton);
				intranetRadioButton.setBounds(670, 170, 100, 25);
				panel.add(intranetRadioButton);
				intranetRadioButton.setText("Intranet");
				fiRadioButton = new JRadioButton();
				fiRadioButton.setFont(new Font("", Font.BOLD, 12));
				fiRadioButton.setEnabled(false);
				buttonGroup.add(fiRadioButton);
				fiRadioButton.setBounds(670, 210, 100, 25);
				panel.add(fiRadioButton);
				fiRadioButton.setText("Fi");
				develRadioButton = new JRadioButton();
				develRadioButton.setFont(new Font("", Font.BOLD, 12));
				develRadioButton.setEnabled(false);
				buttonGroup.add(develRadioButton);
				develRadioButton.setBounds(670, 230, 100, 25);
				panel.add(develRadioButton);
				develRadioButton.setText("Devel");

				deployRadioButton = new JRadioButton();
				deployRadioButton.setFont(new Font("", Font.BOLD, 12));
				deployRadioButton.setEnabled(false);
				buttonGroup.add(deployRadioButton);
				deployRadioButton.setBounds(670, 250, 100, 25);
				panel.add(deployRadioButton);
				deployRadioButton.setText("Deploy");

				validRadioButton = new JRadioButton();
				validRadioButton.setFont(new Font("", Font.BOLD, 12));
				validRadioButton.setEnabled(false);
				buttonGroup.add(validRadioButton);
				validRadioButton.setBounds(670, 270, 100, 25);
				panel.add(validRadioButton);
				validRadioButton.setText("Valid");

				validDvRadioButton = new JRadioButton();
				validDvRadioButton.setFont(new Font("", Font.BOLD, 12));
				validDvRadioButton.setEnabled(false);
				buttonGroup.add(validDvRadioButton);
				validDvRadioButton.setBounds(670, 290, 100, 25);
				panel.add(validDvRadioButton);
				validDvRadioButton.setText("ValidDV");

				customRadioButton = new JRadioButton();
				customRadioButton.addItemListener(new ItemListener() {

					public void itemStateChanged(ItemEvent e) {
						if (customRadioButton.isSelected()) {
							deployDirButton.setEnabled(true);
							deployTextField.setEnabled(true);
						} else {
							deployDirButton.setEnabled(false);
							deployTextField.setEnabled(false);
						}
					}
				});
				customRadioButton.setEnabled(false);
				buttonGroup.add(customRadioButton);
				customRadioButton.setFont(new Font("", Font.BOLD, 12));
				customRadioButton.setBounds(670, 310, 145, 25);
				panel.add(customRadioButton);
				customRadioButton.setText("Custom Target...");
			}
			{
				final JPanel panel = new JPanel();
				panel.setLayout(null);
				tabbedPane.addTab("Default Properties (XML)", null, panel, null);
				{
					final JScrollPane scrollPane = new JScrollPane();
					scrollPane.setBounds(25, 30, 795, 235);
					panel.add(scrollPane);
					{
						propertiesEditorPane = new JEditorPane();
						scrollPane.setViewportView(propertiesEditorPane);
					}
				}
				{
					try {
						BufferedReader in = new BufferedReader(
								new FileReader(properties.getProperty(DEFAULT_PROPERTIES_FILE_PROP)));
						StringBuffer text_buffer = new StringBuffer();
						String line = null;
						while ((line = in.readLine()) != null) {
							text_buffer.append(line);
							text_buffer.append("\n");
						}
						propertiesEditorPane.setText(text_buffer.toString());
					} catch (IOException ioe) {
						ioe.printStackTrace();
					}
				}
				{
					saveButton = new JButton();
					saveButton.addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent e) {
							saveBuildProperties();
						}
					});
					saveButton.setBounds(285, 285, 100, 25);
					panel.add(saveButton);
					saveButton.setText("Save");
					saveButton.setEnabled(false);
				}
				{
					defaultButton = new JButton();
					defaultButton.addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent e) {
							propertiesFile = new File(properties.getProperty(DEFAULT_PROPERTIES_FILE_PROP));
							loadPropertiesEditorPane();
							saveButton.setEnabled(false);
						}
					});
					defaultButton.setBounds(510, 285, 100, 25);
					panel.add(defaultButton);
					defaultButton.setText("Default");
				}
				{
					loadButton = new JButton();
					loadButton.addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent e) {
							loadBuildProperties();
						}
					});
					loadButton.setBounds(170, 285, 100, 25);
					panel.add(loadButton);
					loadButton.setText("Load");
				}
				{
					saveasButton = new JButton();
					saveasButton.addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent e) {
							saveAsBuildProperties();
						}
					});
					saveasButton.setBounds(400, 285, 100, 25);
					panel.add(saveasButton);
					saveasButton.setText("Save as...");
				}
			}
			{
				final JPanel panel = new JPanel();
				panel.setLayout(null);
				tabbedPane.addTab("Output", null, panel, null);
				{
					outputScrollPane = new JScrollPane();
					outputScrollPane.setBounds(25, 25, 790, 265);
					panel.add(outputScrollPane);
					{
						outputTextArea = new JTextArea();
						outputTextArea.setEditable(false);
						outputScrollPane.setViewportView(outputTextArea);
					}
				}
			}
		}
		{
			progressBar = new JProgressBar(0, 100);
			progressBar.setBounds(760, 500, 100, 25);
			progressBar.setStringPainted(true);
			progressBar.setString("");
			getContentPane().add(progressBar);
		}
		goButton = new JButton();
		goButton.setBounds(15, 495, 125, 30);
		goButton.setBackground(new Color(192, 192, 192));
		getContentPane().add(goButton);
		goButton.setText("Go!");
		goButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(OUTPUT_INDEX);
				buildWorker = new SwingWorker() {

					public Object construct() {
						build();
						return "OK";
					}
				};
				// stopButton.setEnabled(true);
				buildWorker.start();
			}
		});
		stopButton = new JButton();
		stopButton.setBounds(160, 495, 125, 30);
		stopButton.setBackground(new Color(192, 192, 192));
		getContentPane().add(stopButton);
		stopButton.setText("Stop");
		stopButton.setEnabled(false);
		stopButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (buildWorker != null) {
					buildWorker.interrupt();
				}
				// stopButton.setEnabled(false);
			}
		});
		{
			chooser = new JFileChooser();
		}
		selectedModuleChanged();
		//
	}
}
