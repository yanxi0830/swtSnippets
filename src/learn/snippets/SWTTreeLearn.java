package learn.snippets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

/*
 * Custom Drawing Table and Tree Items
 * https://www.eclipse.org/articles/article.php?file=Article-CustomDrawingTableAndTreeItems/index.html
 * 
 * SUMMARY
 * - populating a table or tree widget involves creating items and setting their attributes
 * - table or tree takes responsibility for displaying the items
 * 
 * BACKGROUND
 * - tables and trees are powerful tools for presenting data in a structured manner
 * - data is displayed as a collection of items which have attributes like text(s), image(s), checkbox(s)
 * - clients create the items and set their attributes
 * - table or tree takes responsibility for displaying them\
 * - inflexible
 * 		- an item in a table/tree can only contain one image, and it must appear
 * 		  before its text
 * 		- given the range of visual appearances that a client may want an item to have, 
 * 		  the best way to ensure that clients can create items with custom appearances 
 * 		  is to allow items to be drawn
 * 
 * CUSTOM DRAW EVENTS
 * - custom drawing is done on a per-cell basis, where a cell is the portion of an item 
 *   that resides within some row/column of the parent table
 * 
 * The following Table events have been defined to provide hooks into the drawing process
 * - SWT.MeasureItem: allows a client to specify the dimensions of a cell's content
 * - SWT.EraseItem: allows a client to custom draw a cell's background and/or selection, 
 * 					and to influence whether the cell's foreground should be drawn
 * - SWT.PaintItem: allows a client to custom draw or augment a cell's foreground and/or focus rectangle
 * 
 * These events are configured to reflect how the cell would be drawn by default
 * If a client does not hook one of these listeners, then the default cell drawing process
 * will occur.
 *   
 */
public class SWTTreeLearn {
	public static void main(String[] args) {
//		specifyCellWidthHeight();
		packingColumns();
		
	}
	
	private static void specifyCellWidthHeight() {
		final Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setBounds(10, 10, 500, 500);
		final Tree tree = new Tree(shell, SWT.NONE);
		tree.setBounds(10, 10, 300, 400);
		tree.setLinesVisible(true);
		for (int i = 0; i < 5; i++) {
			new TreeItem(tree, SWT.NONE).setText("item " + i);
		}
		tree.addListener(SWT.MeasureItem, event -> {
			int clientWidth = tree.getClientArea().width;
			event.height = event.gc.getFontMetrics().getHeight() * 2;
			event.width = clientWidth * 2;
		});
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) display.sleep();
		}
		display.dispose();
	}
	
	private static void packingColumns() {
		final Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setBounds(10, 10, 600, 300);
		final Table table = new Table(shell, SWT.NONE);
		table.setBounds(10, 10, 500, 200);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		final TableColumn column0 = new TableColumn(table, SWT.NONE);
		column0.setWidth(100);
		final TableColumn column1 = new TableColumn(table, SWT.NONE);
		column1.setWidth(100);
		
		column0.addListener(SWT.Selection, event -> {
			column0.pack();
		});
		
		column1.addListener(SWT.Selection, event -> {
			column1.pack();
		});
		
		for (int i = 0; i < 5; i++) {
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText(0, "item " + i + " col 0");
			item.setText(1, "item " + i + " col 1");
		}
		
		table.addListener(SWT.MeasureItem, event -> {
			event.height *= 2;
			event.width = event.width * 2;
		});
		
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) display.sleep();
		}
		display.dispose();
		
	}
}
