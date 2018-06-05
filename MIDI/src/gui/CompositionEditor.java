package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;

import theroy.CompositionManager;

public class CompositionEditor extends Shell {

	private static int __DEFAULT_TEMPO = 100;
	
	private GridLayout gridLayoutShell;
	
	private Label labelTempo;
	private Spinner spinnerTempo;
	
	private Label labelNumerador;
	private Spinner spinnerNumerador;
	private Label labelDenominador;
	private Spinner spinnerDenominador;
	
	public CompositionEditor(Display display, int style) {
		this.setText("Random composer :: Editor de composición");
		this.setSize(420, 500);
		
		initializeInterface();
		
		CompositionManager.getInstance().setBpm(this.spinnerTempo.getSelection());
		
        loadCompositionData();
	}
	
	protected void checkSubclass() {
		
	}

	public void initializeInterface() {
		this.gridLayoutShell = new GridLayout(2, false);
		this.gridLayoutShell.marginBottom = 5;
		this.gridLayoutShell.marginHeight = 0;
		this.gridLayoutShell.marginLeft = 5;
		this.gridLayoutShell.marginRight = 5;
		this.gridLayoutShell.marginTop = 5;
		this.gridLayoutShell.marginWidth = 0;
		
		this.setLayout(this.gridLayoutShell);
		
		GridData gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		
		this.labelTempo = new Label(this, SWT.NONE);
		this.labelTempo.setText("Tempo:");
		this.labelTempo.setLayoutData(gridData);
		
		gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.verticalAlignment = GridData.FILL;
		
		this.spinnerTempo = new Spinner(this, SWT.NONE);
		this.spinnerTempo.setDigits(0);
		this.spinnerTempo.setIncrement(1);
		this.spinnerTempo.setMaximum(300);
		this.spinnerTempo.setMinimum(20);
		this.spinnerTempo.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				CompositionManager.getInstance().setBpm(spinnerTempo.getSelection());
			}
		});
		this.spinnerTempo.setSelection(__DEFAULT_TEMPO);
		this.spinnerTempo.setLayoutData(gridData);
		
		gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		
		this.labelNumerador = new Label(this, SWT.NONE);
		this.labelNumerador.setText("Numerador:");
		this.labelNumerador.setLayoutData(gridData);
		
		gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.verticalAlignment = GridData.FILL;
		
		this.spinnerNumerador = new Spinner(this, SWT.NONE);
		this.spinnerNumerador.setDigits(0);
		this.spinnerNumerador.setIncrement(1);
		this.spinnerNumerador.setMaximum(12);
		this.spinnerNumerador.setMinimum(2);
		this.spinnerNumerador.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				CompositionManager.getInstance().setNumerador(spinnerNumerador.getSelection());
			}
		});
		this.spinnerNumerador.setSelection(4);
		this.spinnerNumerador.setLayoutData(gridData);
		
		gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		
		this.labelDenominador= new Label(this, SWT.NONE);
		this.labelDenominador.setText("Denominador:");
		this.labelDenominador.setLayoutData(gridData);
		
		gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.verticalAlignment = GridData.FILL;
		
		this.spinnerDenominador = new Spinner(this, SWT.NONE);
		this.spinnerDenominador.setDigits(0);
		this.spinnerDenominador.setIncrement(1);
		this.spinnerDenominador.setMaximum(8);
		this.spinnerDenominador.setMinimum(2);
		this.spinnerDenominador.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				CompositionManager.getInstance().setBpm(spinnerDenominador.getSelection());
			}
		});
		this.spinnerDenominador.setSelection(4);
		this.spinnerDenominador.setLayoutData(gridData);
	}
	
	public void loadCompositionData() {
		
	}
}