package learn.snippets;

/*
 * Button example snippet: a Button with text and image
 *
 * For a list of all SWT example snippets see
 * http://www.eclipse.org/swt/snippets/
 *
 * @since 3.2
 */
import org.eclipse.swt.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

public class Snippet206 {

	public static void main(String[] args) {
		// Display and Shell, and Image
		Display display = new Display();
		Image image = display.getSystemImage(SWT.ICON_QUESTION);
		Shell shell = new Shell(display);
		shell.setLayout (new GridLayout());
		
		// Create new button with image
		Button button = new Button(shell, SWT.PUSH);
		button.setImage(image);
		button.setText("Button");
		
		// Open Shell and Main Loop
		shell.setSize(300, 300);
		shell.open();
		while (!shell.isDisposed ()) {
			if (!display.readAndDispatch ()) display.sleep ();
		}
		display.dispose ();
	}
}

