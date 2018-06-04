package projects;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;

public class TextEditor {
	public static void main(String[] args) {
		/* Create Display and Shell */
		Display display = new Display();
		Shell shell = new Shell(display);
		
		/* Create StyledText widget and set the layout/layout data */ 
		shell.setLayout(new FillLayout());
		StyledText styledText = new StyledText(shell, SWT.BORDER);
		styledText.setToolTipText("Start Typing...");
		
		/* Create the MenuBar, as well as the headers */
		Menu menuBar = new Menu(shell, SWT.BAR);
		MenuItem fileMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
		fileMenuHeader.setText("&File");
		
		MenuItem editMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
		editMenuHeader.setText("&Edit");
		
		/* Create the drop down Menus */
		Menu fileMenu = new Menu(shell, SWT.DROP_DOWN);
		fileMenuHeader.setMenu(fileMenu);
		
		Menu editMenu = new Menu(shell, SWT.DROP_DOWN);
		editMenuHeader.setMenu(editMenu);
		
		/* Create the MenuItems */
		MenuItem fileExitItem = new MenuItem(fileMenu, SWT.CASCADE);
		fileExitItem.setText("E&xit");
		
		MenuItem editPasteItem = new MenuItem(editMenu, SWT.CASCADE);
		editPasteItem.setText("Paste");
	
		/* Add the MenuItem listeners */
		/* File -> Exit */
		fileExitItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				display.dispose();
			}
		});
		
		/* Edit -> Paste */
		editPasteItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				styledText.paste();
			}
		});

		/* Set the MenuBar to the Shell and open the Shell */
		shell.setMenuBar(menuBar);
		shell.open();
		/* Main loop */
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}
}
