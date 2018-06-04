package projects;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class TodoListApp {
	public static void main(String[] args) {
		// Create Display and Shell
		Display display = new Display();
		Shell shell = new Shell(display);

		/* Create Layout and set it as the Shell layout */
		 GridLayout gridLayout = new GridLayout(2, false);	
		 shell.setLayout(gridLayout);

		/* Add Text widget for input of new items */
		 Text textWidget = new Text(shell, SWT.BORDER);

		/* Create and add GridData for the Text widget */
		 GridData gridData = new GridData();
		 gridData.horizontalAlignment = GridData.FILL;
		 gridData.grabExcessHorizontalSpace = true;
		 textWidget.setLayoutData(gridData);

		/* 
		 * Add a Label next to the Text widget
		 * that explains how to add items to the list
		 */
		 Label label = new Label(shell, SWT.NONE);
		 label.setText("Press Enter to add the item to the list");

		/* Create and add GridData for instructions Label */
		 gridData = new GridData();
		 gridData.horizontalAlignment = GridData.FILL;
		 label.setLayoutData(gridData);

		/* Create the List widget to display the todo list */
		 List list = new List(shell, 0);
		 
		
		/* Create and add GridData for the List */
		 gridData = new GridData();
		 gridData.grabExcessVerticalSpace = true;
		 gridData.grabExcessHorizontalSpace = true;
		 gridData.horizontalAlignment = GridData.FILL;
		 gridData.verticalAlignment = GridData.FILL;
		 gridData.horizontalSpan = 2;
		 list.setLayoutData(gridData);
		
		/* Create a Button to remove items from the List */
		 Button removeButton = new Button(shell, 0);
		 removeButton.setText("Remove Item");
		
		/* Create and add GridData for the Button */
		 gridData = new GridData();
		 gridData.horizontalAlignment = GridData.FILL;
		 removeButton.setLayoutData(gridData);
		
		/* Create a Label which instructs the user how to delete one or more items */
		 Label removeLabel = new Label(shell, 0);
		 removeLabel.setText("Select item and click remove to delete it");
		
		/* Add listener(s) for the Text input widget */
		 textWidget.addSelectionListener(new SelectionAdapter() {
			 public void widgetDefaultSelected(SelectionEvent e) {
				 String newText = textWidget.getText();
				 list.add(newText);
				 textWidget.setText("");
			 }
		 });
		 
		/* Add listener(s) for the remove Button widget */
		 removeButton.addMouseListener(MouseListener.mouseDownAdapter(e -> {
			 int selectedIndex = list.getSelectionIndex();
			 if (selectedIndex != -1) {
				 list.remove(selectedIndex);
			 }
		 }));
		
		/* Set the Shell to an appropriate size and open it */
		 shell.setSize(500, 500);
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