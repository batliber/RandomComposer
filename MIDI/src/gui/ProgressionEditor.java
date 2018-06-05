package gui;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import theroy.Mode;
import theroy.Note;
import theroy.Stage;
import timing.ProgressionManager;

public class ProgressionEditor extends Shell {

	private GridLayout gridLayoutShell;
	
	private Label labelStages;
	private Combo comboStages;
	
	private Label labelModulateTo;
	private Combo comboModulateToKey;
	private Combo comboModulateToMode;
	
	private Button buttonAddStage;
	
	private Table tableProgression;
	
	public ProgressionEditor(Display display, int style) {
		this.setText("Random composer :: Editor de progresiones");
		this.setSize(420, 500);
        
        initializeInterface();
        loadProgressionData();
	}
	
	protected void checkSubclass() {
		
	}
	
	public void initializeInterface() {
		this.gridLayoutShell = new GridLayout(6, false);
		this.gridLayoutShell.marginBottom = 5;
		this.gridLayoutShell.marginHeight = 0;
		this.gridLayoutShell.marginLeft = 5;
		this.gridLayoutShell.marginRight = 5;
		this.gridLayoutShell.marginTop = 5;
		this.gridLayoutShell.marginWidth = 0;
		
		this.setLayout(this.gridLayoutShell);
		
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		
		labelStages = new Label(this, SWT.NONE);
		labelStages.setText("Estado:");
		labelStages.setLayoutData(gridData);
		
		gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.verticalAlignment = GridData.FILL;
		
		ArrayList<String> items = new ArrayList<String>();
		for (Stage stage : Stage.values()) {
			items.add(stage.name());
		}
		
		this.comboStages = new Combo(this, SWT.NONE);
		this.comboStages.setItems(items.toArray(new String[] {}));
		this.comboStages.setData(items);
		this.comboStages.setLayoutData(gridData);
		
		gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		
		labelModulateTo = new Label(this, SWT.NONE);
		labelModulateTo.setText("Modular a:");
		labelModulateTo.setLayoutData(gridData);
		
		gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.verticalAlignment = GridData.FILL;
		
		items = new ArrayList<String>();
		for (Note note : Note.values()) {
			items.add(note.name());
		}
		
		this.comboModulateToKey = new Combo(this, SWT.NONE);
		this.comboModulateToKey.setItems(items.toArray(new String[] {}));
		this.comboModulateToKey.setData(items);
		this.comboModulateToKey.setLayoutData(gridData);
		
		gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.verticalAlignment = GridData.FILL;
		
		items = new ArrayList<String>();
		for (Mode mode : Mode.values()) {
			items.add(mode.name());
		}
		
		this.comboModulateToMode = new Combo(this, SWT.NONE);
		this.comboModulateToMode.setItems(items.toArray(new String[] {}));
		this.comboModulateToMode.setData(items);
		this.comboModulateToMode.setLayoutData(gridData);
		
		gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		
		this.buttonAddStage = new Button(this, SWT.NONE);
		this.buttonAddStage.setText("Agregar estado");
		this.buttonAddStage.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				Combo combo = comboStages;
				
				Stage stage = Stage.valueOf(((ArrayList<String>)combo.getData()).get(combo.getSelectionIndex()));
				
				TableItem tableItem = new TableItem(tableProgression, SWT.NONE);
				tableItem.setData(stage);
				tableItem.setText(new String[] {
					null,
					stage.name(),
					((ArrayList<String>)comboModulateToKey.getData()).get(comboModulateToKey.getSelectionIndex()),
					((ArrayList<String>)comboModulateToMode.getData()).get(comboModulateToMode.getSelectionIndex()),
				});
				
				ProgressionManager.getInstance().addStage(stage);
			}
		});
		this.buttonAddStage.setLayoutData(gridData);
		
		gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessVerticalSpace = true;
		gridData.verticalAlignment = GridData.FILL;
		gridData.horizontalSpan = 6;
		
		this.tableProgression = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		this.tableProgression.setHeaderVisible(true);
		this.tableProgression.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				ProgressionManager.getInstance().removeStage(tableProgression.getSelectionIndex());
				
				tableProgression.remove(tableProgression.getSelectionIndex());
			}			
		});
		this.tableProgression.setLayoutData(gridData);
		
		TableColumn tableColumnDummy = new TableColumn(this.tableProgression, SWT.NONE);
        tableColumnDummy.setWidth(0);
        
        TableColumn tableColumn = new TableColumn(this.tableProgression, SWT.LEFT);
        tableColumn.setWidth(170);
        tableColumn.setText("Estado");
        
        tableColumn = new TableColumn(this.tableProgression, SWT.LEFT);
        tableColumn.setWidth(100);
        tableColumn.setText("Clave");
        
        tableColumn = new TableColumn(this.tableProgression, SWT.LEFT);
        tableColumn.setWidth(100);
        tableColumn.setText("Modo");
	}

	public void loadProgressionData() {
		tableProgression.setItemCount(0);
		
		for (Stage stage : ProgressionManager.getInstance().listStages()) {
			TableItem tableItem = new TableItem(tableProgression, SWT.NONE);
			tableItem.setData(stage);
			tableItem.setText(new String[] {
				null,
				stage.name()
			});
		}
	}
}