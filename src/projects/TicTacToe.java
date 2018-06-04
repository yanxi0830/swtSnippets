package projects;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

public class TicTacToe {
	
	// X is first player. O is second player.
	static boolean firstPlayer = true;
	
	public static void main(String[] args) {
		
		// Array to reference buttons so we can reset them via menu.
		ArrayList<Button> buttons = new ArrayList<>();
		
		/* Create Display and Shell */
		Display display = new Display();
		Shell shell = new Shell(display);
		
		// Create a Red/Blue/Black color.
		Color red = display.getSystemColor(SWT.COLOR_RED);
		Color blue = display.getSystemColor(SWT.COLOR_BLUE);
		Color black = display.getSystemColor(SWT.COLOR_BLACK);
		
		// 1) Add an "Actions" menu with "New game" and "Exit" menu items.
		//		See ShellMenuExample.java for inspiration.
		Menu bar = new Menu(shell, SWT.BAR);
		shell.setMenuBar(bar);
		
		MenuItem actionsItem = new MenuItem(bar, SWT.CASCADE);
		actionsItem.setText("&Actions");
		Menu actionsMenu = new Menu(shell, SWT.DROP_DOWN);
		actionsItem.setMenu(actionsMenu);
		MenuItem newGameItem = new MenuItem(actionsMenu, SWT.CASCADE);
		newGameItem.setText("New Game");
		MenuItem exitItem = new MenuItem(actionsMenu, SWT.CASCADE);
		exitItem.setText("Exit");
		
		// The "New game" menu should set the text of all buttons in the 'buttons' array to "-"
		// and set the foreground color to be black.
		// It should also set firstPlayer = true;
		newGameItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				firstPlayer = true;
				for (Button b : buttons) {
					b.setText("-");
					b.setForeground(black);
				}
			}
		});
		
		exitItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				display.dispose();
			}
		});
		
		// 2) Add a grid layout to shell.
		//    Grid should have 3 columns. See GridLayoutExample.java for inspiration.
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 3;
		shell.setLayout(gridLayout);
		
		// Create 9 SWT.PUSH buttons.
		for (int i = 1; i <= 9; i++) {
			Button b = new Button(shell, SWT.PUSH);
			
			// Add all buttons to our buttons array:
			buttons.add(b);
			
			// Set each button to text of "-"
			b.setText("-");
			
			// Add a mouse listener to each button that:
//			myButton.addMouseListener(MouseListener.mouseDownAdapter(e -> {
//				System.out.println("Lambda: Button clicked. Executing a lambda");
//			}));
			
			b.addMouseListener(MouseListener.mouseDownAdapter(e -> {
				if (firstPlayer) {
					b.setText("X");
					b.setForeground(red);
					firstPlayer = false;
				} else {
					b.setText("O");
					b.setForeground(blue);
					firstPlayer = true;
				}
			}));
			
				// If it's 'firstPlayer's turn,
					// set text to "X"
					// set foreground color to red.
					// set firstPlayer to be false:
				    // firstPlayer = false;
			
				// If (!firstPlayer) then: "O", blue,   firstplayer->true.
		}
		
		
		// BONUS:
		// Instead of using Buttons, use canvas.
//		
//		// Sample to draw a widget with a circle inside it.
//		Canvas circleCanvas = new Canvas(shell, SWT.NONE);
//		circleCanvas.setSize(50, 50);
//		circleCanvas.addPaintListener(event -> {
//			Rectangle rect = circleCanvas.getClientArea();
//			event.gc.drawOval(0, 0, rect.width - 1, rect.height - 1);
//		});
//
//		// Sample to draw a widget with X inside it.
//		Canvas xCanvas = new Canvas(shell, SWT.NONE);
//		xCanvas.setSize(50, 50);
//		xCanvas.addPaintListener(event -> {
//			event.gc.drawLine(0, 0, 50, 50); //  line like: \
//			event.gc.drawLine(0, 50, 50, 0); //  line like: /
//		});


		// BONUS:
		// Implement "Win" detection. Show which player won in a popup.
		
		
		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		
		// Dispose colors:
		red.dispose();
		blue.dispose();
		black.dispose();
		
		display.dispose();
	}
}
