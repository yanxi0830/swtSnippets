package grive;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

public class GriveGUI {
	public static void main(String[] args) {
		// Create Display and Shell
		Display display = new Display();
		Shell shell = new Shell(display);
		
		/* Create Layout and set it as the Shell layout */
		GridLayout gridLayout = new GridLayout(6, false);
		shell.setLayout(gridLayout);
		
		/* Create the MenuBar and headers */
		Menu menuBar = new Menu(shell, SWT.BAR);
		MenuItem helpMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
		helpMenuHeader.setText("&Help");
		
		Menu helpMenu = new Menu(shell, SWT.DROP_DOWN);
		helpMenuHeader.setMenu(helpMenu);
		
		MenuItem helpAboutItem = new MenuItem(helpMenu, SWT.CASCADE);
		helpAboutItem.setText("About");
		
		GridData gridData = new GridData();
		
		/* Select Directory */
		Label dirLabel = new Label(shell, 0);
		dirLabel.setText("Google Drive Directory: ");
		dirLabel.setLayoutData(gridData);

		Label chosenDirLabel = new Label(shell, 0);
		chosenDirLabel.setText("Please choose a directory");
		gridData = new GridData();
		gridData.horizontalSpan = 3;
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		chosenDirLabel.setLayoutData(gridData);
		
		Button chooseDirButton = new Button(shell, 0);
		chooseDirButton.setText("Choose Directory");
		gridData = new GridData();
		gridData.horizontalSpan = 2;
		chooseDirButton.setLayoutData(gridData);
		
		chooseDirButton.addMouseListener(MouseListener.mouseDownAdapter(e -> {
			DirectoryDialog dirDialog = new DirectoryDialog(shell);
			String dirPath = dirDialog.open();
			System.out.print(dirPath);
			chosenDirLabel.setText(dirPath);
		}));
		
		Label optionsLabel = new Label(shell, 0);
		optionsLabel.setText("Options: ");
		
		Button downloadButton = new Button(shell, SWT.CHECK);
		downloadButton.setText("Force Download");
		Button uploadButton = new Button(shell, SWT.CHECK);
		uploadButton.setText("Upload Only");
		Button noRemoteButton = new Button(shell, SWT.CHECK);
		noRemoteButton.setText("No Remote New");
		gridData = new GridData();
		gridData.horizontalSpan = 3;
		noRemoteButton.setLayoutData(gridData);
		
		Group speedFrame = new Group(shell, SWT.BORDER);
		RowLayout rowLayout = new RowLayout();
		rowLayout.fill = true;
		rowLayout.center = true;
		rowLayout.justify  = true;
		speedFrame.setLayout(rowLayout);
		gridData = new GridData();
		gridData.horizontalSpan = 6;
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		speedFrame.setLayoutData(gridData);
		speedFrame.setText("Speed Limit");
		
		Label uploadLabel = new Label(speedFrame, 0);
		uploadLabel.setText("Upload Speed:");
		
		Combo uploadCombo = new Combo(speedFrame, SWT.READ_ONLY);
		String speeds[] = {"Unlimited", "100", "200", "300", "500"};
		uploadCombo.setItems(speeds);
		
		Label downloadLabel = new Label(speedFrame, 0);
		downloadLabel.setText("Download Speed:");
		
		Combo downloadCombo = new Combo(speedFrame, SWT.READ_ONLY);
		downloadCombo.setItems(speeds);
		
		Group messageFrame = new Group(shell, SWT.BORDER);
		messageFrame.setLayout(rowLayout);
		messageFrame.setLayoutData(gridData);
		messageFrame.setText("Message");
		
		
		Button authButton = new Button(shell, 0);
		authButton.setText("Authenticate");
		gridData = new GridData();
		gridData.horizontalSpan = 4;
		authButton.setLayoutData(gridData);
		
		Button quitButton = new Button(shell, 0);
		gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		quitButton.setText("Quit");
		quitButton.setLayoutData(gridData);
		
		Button syncButton = new Button(shell, 0);
		syncButton.setText("Sync");
		syncButton.setLayoutData(gridData);
		
		/* Set the Shell to an appropriate size and open it */
		shell.setMenuBar(menuBar);
		shell.setSize(800, 300);
		shell.open();

		// Main loop
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
		
	}
}
